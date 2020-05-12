package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Album;
import lt.vu.entities.Artist;
import lt.vu.entities.Song;
import lt.vu.persistence.AlbumsDAO;
import lt.vu.persistence.SongsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class Albums {
    @Inject
    private AlbumsDAO albumsDAO;

    @Getter
    @Setter
    private Album songToCreate = new Album();

    @Getter
    private List<Song> allSongs;

    @lombok.Getter
    @lombok.Setter
    private Album album;

    @Inject
    private SongsDAO songsDAO;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer albumId = Integer.parseInt(requestParameters.get("albumId"));
        this.album = albumsDAO.findOne(albumId);
        this.allSongs = songsDAO.findByAlbum(this.album);
    }


}

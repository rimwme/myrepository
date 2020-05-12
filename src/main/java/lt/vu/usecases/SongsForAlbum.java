package lt.vu.usecases;

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
import java.util.Map;

@Model
public class SongsForAlbum {
    @Inject
    private AlbumsDAO albumsDAO;

    @Inject
    private SongsDAO songsDAO;

    @lombok.Getter
    @lombok.Setter
    private Album album;

    @lombok.Getter
    @lombok.Setter
    private Song songToCreate = new Song();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer albumId = Integer.parseInt(requestParameters.get("albumId"));
        this.album = albumsDAO.findOne(albumId);
    }

    @Transactional
    public String createSong() {
        songToCreate.setArtist(this.album.getArtist());
        songToCreate.setAlbum(this.album);
        songsDAO.persist(songToCreate);
        return "albumDetails?faces-redirect=true&albumId=" + this.album.getId();
    }
}

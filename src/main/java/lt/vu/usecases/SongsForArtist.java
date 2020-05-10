package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Album;
import lt.vu.entities.Artist;
import lt.vu.entities.Song;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.AlbumsDAO;
import lt.vu.persistence.ArtistDAO;
import lt.vu.persistence.SongsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class SongsForArtist {

    @Inject
    private ArtistDAO artistDAO;

    @Inject
    private SongsDAO songsDAO;

    @lombok.Getter
    @lombok.Setter
    private Artist artist;

    @lombok.Getter
    @lombok.Setter
    private Song songToCreate = new Song();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer artistId = Integer.parseInt(requestParameters.get("artistId"));
        this.artist = artistDAO.findOne(artistId);
    }

    @Transactional
    @LoggedInvocation
    public String createSong() {
        songToCreate.setArtist(this.artist);
        songsDAO.persist(songToCreate);
        return "artist?faces-redirect=true&artistId=" + this.artist.getId();
    }
}

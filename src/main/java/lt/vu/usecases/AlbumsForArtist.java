package lt.vu.usecases;

import lt.vu.entities.Album;
import lt.vu.entities.Artist;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.AlbumsDAO;
import lt.vu.persistence.ArtistDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class AlbumsForArtist {

    @Inject
    private ArtistDAO artistDAO;

    @Inject
    private AlbumsDAO albumsDAO;

    @lombok.Getter
    @lombok.Setter
    private Artist artist;

    @lombok.Getter
    @lombok.Setter
    private Album albumToCreate = new Album();

    @lombok.Getter
    @lombok.Setter
    private String nameForUpdate;

    @lombok.Getter
    @lombok.Setter
    private Integer yearForUpdate;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer artistId = Integer.parseInt(requestParameters.get("artistId"));
        this.artist = artistDAO.findOne(artistId);
    }

    @Transactional
    @LoggedInvocation
    public String createAlbum() {
        albumToCreate.setArtist(this.artist);
        albumsDAO.persist(albumToCreate);
        return "artist?faces-redirect=true&artistId=" + this.artist.getId();
    }

    @Transactional
    @LoggedInvocation
    public String updateArtist() throws InterruptedException {
        artist.setName(nameForUpdate);
        artist.setYear(yearForUpdate);

        try {
            Thread.sleep(5000);
            artistDAO.update(artist);
            artistDAO.flush();
        } catch ( OptimisticLockException e) {
            return "artist?faces-redirect=true&artistId=" + artist.getId() + "&error=optimistic-lock-exception";
        }


        return "artist?faces-redirect=true&artistId=" + artist.getId();
    }
}

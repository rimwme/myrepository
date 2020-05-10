package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Artist;
import lt.vu.persistence.ArtistDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class Artists {

    @Inject
    private ArtistDAO artistDAO;

    @Getter
    @Setter
    private Artist artistToCreate = new Artist();

    @Getter
    private List<Artist> allArtists;

    @PostConstruct
    public void init(){
        loadAllArtists();
   /*     Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer artistId = Integer.parseInt(requestParameters.get(""));
*/
    }

    @Transactional
    public String createArtist(){
        this.artistDAO.persist(artistToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllArtists(){
        this.allArtists = artistDAO.loadAll();
    }
}

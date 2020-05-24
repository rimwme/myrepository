package lt.vu.usecases;

import lt.vu.entities.Album;
import lt.vu.entities.Author;
import lt.vu.entities.Song;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.AuthorsDAO;
import lt.vu.persistence.SongsDAO;
import lt.vu.usecases.PriceCalculations.PriceCalculator;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class AuthorsForSongs {

    @Inject
    private SongsDAO songsDAO;

    @Inject
    private AuthorsDAO authorsDAO;

    @Inject
    private PriceCalculator priceCalculator;

    @lombok.Getter
    @lombok.Setter
    private Song song;

    @lombok.Getter
    @lombok.Setter
    private Author authorToCreate = new Author();

    @lombok.Getter
    @lombok.Setter
    private double price;


    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer songId = Integer.parseInt(requestParameters.get("songId"));
        this.song = songsDAO.findOne(songId);
        price = priceCalculator.calculatePrice(this.song);
    }

    @LoggedInvocation
    @Transactional
    public String createAuthor() {
        List<Author> auth = authorsDAO.findByName(authorToCreate.getName(), authorToCreate.getLastName());

        if(auth.isEmpty()){
            authorsDAO.persist(authorToCreate);
            song.addAuthor(authorToCreate);
        }else{
            song.addAuthor(auth.get(0));
        }

        return "songDetails?faces-redirect=true&songId=" + this.song.getId();
    }
}

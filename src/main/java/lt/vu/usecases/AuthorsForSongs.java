package lt.vu.usecases;

import lt.vu.entities.Album;
import lt.vu.entities.Author;
import lt.vu.entities.Song;
import lt.vu.persistence.AuthorsDAO;
import lt.vu.persistence.SongsDAO;

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

    @lombok.Getter
    @lombok.Setter
    private Song song;

    @lombok.Getter
    @lombok.Setter
    private Author authorToCreate = new Author();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer songId = Integer.parseInt(requestParameters.get("songId"));
        this.song = songsDAO.findOne(songId);
    }

    @Transactional
    public String createAuthor() {
       /* song.setAuthors();
        List<Song> songs = authorToCreate.getSongs();
        songs.add(song);
        authorToCreate.setSongs(songs);
        authorsDAO.persist(authorToCreate);*/

       //Need to check if author already exists


        List<Author> auth = authorsDAO.findByName(authorToCreate.getName(), authorToCreate.getLastName());

        System.out.println("-------------iiiiiiiii-------------iiiiiiii--------- " + auth);
        if(auth.isEmpty()){
            authorsDAO.persist(authorToCreate);
            song.addAuthor(authorToCreate);
        }else{
            System.out.println("--------------------------------------- " + auth.get(0));
            song.addAuthor(auth.get(0));
        }



        return "songDetails?faces-redirect=true&songId=" + this.song.getId();
    }
}

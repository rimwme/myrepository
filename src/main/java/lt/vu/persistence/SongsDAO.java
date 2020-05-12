package lt.vu.persistence;

import lt.vu.entities.Album;
import lt.vu.entities.Song;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class SongsDAO {
    @Inject
    private EntityManager em;

    public void persist(Song song) { this.em.persist(song); }

    public Song findOne(Integer id) { return em.find(Song.class, id); }

    public List<Song> loadAll() {
        return em.createNamedQuery("Song.findAll", Song.class).getResultList();
    }

    public List<Song> findByAlbum(Album album) {
        return em.createNamedQuery("Song.findByAlbum", Song.class).setParameter("album", album ).getResultList();
    }
}

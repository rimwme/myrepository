package lt.vu.persistence;

import lt.vu.entities.Artist;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ArtistDAO {

    @Inject
    private EntityManager em;

    public void persist(Artist artist) { this.em.persist(artist); }

    public Artist findOne(Integer id) { return em.find(Artist.class, id); }

    public Artist update(Artist artist){
        return em.merge(artist);
    }

    public List<Artist> loadAll() {
        return em.createNamedQuery("Artist.findAll", Artist.class).getResultList();
    }
}

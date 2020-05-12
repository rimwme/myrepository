package lt.vu.persistence;


import lt.vu.entities.Album;
import lt.vu.entities.Author;
import lt.vu.entities.Song;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class AuthorsDAO {
    @Inject
    private EntityManager em;

    public void persist(Author author) { this.em.persist(author); }

    public Author findOne(Integer id) { return em.find(Author.class, id); }

    public List<Author> loadAll() {
        return em.createNamedQuery("Author.findAll", Author.class).getResultList();
    }

    public List<Author> findByName(String name, String lastName) {
        return em.createNamedQuery("Author.findByName", Author.class).setParameter("name", name )
                .setParameter("lastName", lastName).getResultList();
    }
}

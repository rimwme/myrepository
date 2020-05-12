package lt.vu.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Author.findAll", query = "select a from Author as a"),
        @NamedQuery(name = "Author.findByName", query = "select a from Author as a where a.name like : name and a.lastName like: lastName")
})
@Table(name = "AUTHOR")
@Getter
@Setter
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Size(max = 50)
    @Column(name = "LAST_NAME")
    private String lastName;

    @ManyToMany(mappedBy = "authors")
    private List<Song> songs = new ArrayList<>();
}

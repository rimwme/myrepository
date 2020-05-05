package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Song.findAll", query = "select s from Song as s")
})
@Table(name = "SONG")
@Getter
@Setter
public class Song implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Column(name = "DURATION")
    private Integer duration;

    @Column(name = "RELEASE_DATE")
    private Date releaseDate;

    @ManyToOne
    @JoinColumn(name = "ALBUM_ID")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "ARTIST_ID")
    private Artist artist;

    @ManyToMany
    @JoinTable(name = "SONG_AUTHOR")
    private List<Author> authors = new ArrayList<>();
    //private Set<Author> authors() { return authors; }
}

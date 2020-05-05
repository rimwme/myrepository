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
        @NamedQuery(name = "Artist.findAll", query = "select a from Artist as a")
})
@Table(name = "ARTIST")
@Getter
@Setter
public class Artist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Column(name = "START_YEAR")
    private Integer year;

    @OneToMany(mappedBy = "artist")
    private List<Song> songs = new ArrayList<>();
}

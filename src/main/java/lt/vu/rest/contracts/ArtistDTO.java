package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.vu.entities.Artist;
import lt.vu.entities.Song;

@Getter
@Setter
@NoArgsConstructor
public class ArtistDTO {
    private Integer id;
    private String name;
    private Integer year;

    public ArtistDTO(Artist artist) {
        this.id = artist.getId();
        this.name = artist.getName();
        this.year = artist.getYear();
    }
}

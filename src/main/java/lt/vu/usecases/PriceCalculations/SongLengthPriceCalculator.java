package lt.vu.usecases.PriceCalculations;

import lt.vu.entities.Song;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

@ApplicationScoped
@Specializes
public class SongLengthPriceCalculator extends PriceCalculator {

    public double calculatePrice(Song song)
    {
        if (song.getDuration() > 200) {
            return 1.99;
        } else {
            return  0.99;
        }
    }
}

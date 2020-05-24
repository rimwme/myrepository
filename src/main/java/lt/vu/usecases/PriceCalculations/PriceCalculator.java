package lt.vu.usecases.PriceCalculations;

import lt.vu.entities.Song;

import javax.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class PriceCalculator {

    public double calculatePrice(Song song)
    {
        return 0.99;
    }
}

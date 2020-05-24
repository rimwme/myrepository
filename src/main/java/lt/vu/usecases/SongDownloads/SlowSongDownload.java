package lt.vu.usecases.SongDownloads;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
public class SlowSongDownload implements SongDownload{
    @Override
    public String songDownload(Integer songId) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("--- Ended slow song download " + songId);
        return "Song Downloaded";
    }
}

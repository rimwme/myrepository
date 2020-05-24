package lt.vu.usecases.SongDownloads;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FastSongDownload implements SongDownload {

    @Override
    public String songDownload(Integer songId) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("--- Ended fast song download " + songId);
        return "Song Downloaded";
    }
}

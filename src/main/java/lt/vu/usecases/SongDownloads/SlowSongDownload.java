package lt.vu.usecases.SongDownloads;

import lt.vu.interceptors.LoggedInvocation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
public class SlowSongDownload implements SongDownload{

    @Override
    @LoggedInvocation
    public String songDownload(Integer songId) {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(" Ended slow song download " + songId);
        return "Song Downloaded";
    }
}

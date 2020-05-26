package lt.vu.usecases.SongDownloads;

import lt.vu.interceptors.LoggedInvocation;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FastSongDownload implements SongDownload {

    @Override
    @LoggedInvocation
    public String songDownload(Integer songId) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(" Ended fast song download " + songId);
        return "Song Downloaded";
    }
}

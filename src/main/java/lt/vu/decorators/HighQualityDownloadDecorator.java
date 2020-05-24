package lt.vu.decorators;

import lt.vu.usecases.SongDownloads.SongDownload;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class HighQualityDownloadDecorator implements SongDownload {

    @Inject @Delegate @Any
    SongDownload songDownload;

    @Override
    public String songDownload(Integer songId) {
        int internetSpeed = getInternetSpeed();

        if (internetSpeed > 2)
        {
            System.out.println("Changing to a better server");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        return songDownload.songDownload(songId);
    }

    private int getInternetSpeed() {
        return 5;
    }
}

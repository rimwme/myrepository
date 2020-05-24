package lt.vu.usecases.SongDownloads;

import java.io.Serializable;

public interface SongDownload extends Serializable {
    String songDownload(Integer songId);
}

package lt.vu.usecases;

import lt.vu.interceptors.LoggedInvocation;
import lt.vu.usecases.SongDownloads.SongDownload;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class DownloadSong implements Serializable {
    @Inject
    private SongDownload songDownload;
    private Map<Integer, CompletableFuture<String>> downloadStatuses = new HashMap<>();

    @LoggedInvocation
    public String downloadSong() throws ExecutionException, InterruptedException {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        Integer songId = Integer.parseInt(requestParameters.get("songId"));

        if (!downloadStatuses.containsKey(songId) || downloadStatuses.get(songId).isDone()) {
            downloadStatuses.put(songId, CompletableFuture.supplyAsync(() -> songDownload.songDownload(songId)));
        }

        return "songDetails?faces-redirect=true&songId=" + songId;
    }


    @LoggedInvocation
    public String getDownloadingStatus(Integer songId) throws ExecutionException, InterruptedException {
        if (!downloadStatuses.containsKey(songId) || downloadStatuses.get(songId) == null) {
            return "Not downloading";
        } else if (downloadStatuses.get(songId).isDone()) {
            return downloadStatuses.get(songId).get();
        } else {
            return "Downloading";
        }
    }

}

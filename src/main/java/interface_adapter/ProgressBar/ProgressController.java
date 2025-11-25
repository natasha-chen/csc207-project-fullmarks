package interface_adapter.ProgressBar;

import use_case.progress.ProgressInputBoundary;
/**
 * Handles requests such as starting a download or cancelling it.
 */
public class ProgressController {

    private final DownloadInputBoundary downloadInteractor;
    private final ProgressInputBoundary progressInteractor;

    public ProgressController(DownloadInputBoundary downloadInteractor,
                              ProgressInputBoundary progressInteractor) {

        this.downloadInteractor = downloadInteractor;
        this.progressInteractor = progressInteractor;
    }

    /**
     * Starts the long-running process (download or conversion).
     *
     * @param url the URL of the video to download
     */
    public void startDownload(String url) {
        downloadInteractor.execute(url);  /**  depends on the download interacter to do the download action */
    }

    /**
     * Cancels the long-running process.
     */
    public void cancelDownload() {
        progressInteractor.cancel();
    }
}

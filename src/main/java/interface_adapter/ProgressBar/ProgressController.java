package interface_adapter.ProgressBar;

import use_case.download.DownloadInputBoundary;
import use_case.download.DownloadInputData;
import use_case.progress.ProgressInputBoundary;

/**
 * Handles requests such as starting a download or cancelling it.
 */
public class ProgressController {

    private DownloadInputBoundary downloadInteractor;  // ← no longer final
    private final ProgressInputBoundary progressInteractor;

    public ProgressController(DownloadInputBoundary downloadInteractor,
                              ProgressInputBoundary progressInteractor) {
        this.downloadInteractor = downloadInteractor;
        this.progressInteractor = progressInteractor;
    }

    /**
     * Allows AppBuilder to inject the DownloadInteractor
     * after both use cases are created.
     */
    public void setDownloadInteractor(DownloadInputBoundary downloadInteractor) {
        this.downloadInteractor = downloadInteractor;
    }

    /**
     * Starts the long-running process (download or conversion).
     *
     * @param url the URL of the video to download
     * @param outputFolder the folder where the video should be saved
     */
    public void startDownload(String url, String outputFolder) {
        DownloadInputData inputData = new DownloadInputData(url, outputFolder);

        Thread worker = new Thread(() -> {
            downloadInteractor.execute(inputData);
        });
        worker.start();
    }

    /**
     * Cancels the long-running process.
     */
    public void cancelDownload() {
        progressInteractor.cancel();
    }
}

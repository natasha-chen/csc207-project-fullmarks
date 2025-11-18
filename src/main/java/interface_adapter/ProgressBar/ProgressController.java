package interface_adapter.ProgressBar;

import use_case.progress.ProgressInputBoundary;
/**
 * Handles requests such as starting a download or cancelling it.
 */
public class ProgressController {

    private final ProgressInputBoundary interactor;

    public ProgressController(ProgressInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Starts the long-running process (download or conversion).
     *
     * @param url the URL of the video to download
     */
    public void startDownload(String url) {
        interactor.execute(url);  /**  depends on the download interacter to do the download action */
    }

    /**
     * Cancels the long-running process.
     */
    public void cancelDownload() {
        interactor.cancel();
    }
}

package interface_adapter.download;

import use_case.download.DownloadInputBoundary;
import use_case.download.DownloadInputData;

public class DownloadController {

    private final DownloadInputBoundary interactor;

    public DownloadController(DownloadInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Called by the view when the user clicks "Download".
     */
    public void execute(String url, String outputFolder) {
        DownloadInputData inputData = new DownloadInputData(url, outputFolder);
        new Thread (() -> interactor.execute(inputData)).start();
    }
}
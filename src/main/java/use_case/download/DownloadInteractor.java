package use_case.download;

import use_case.progress.ProgressInputBoundary;

public class DownloadInteractor implements DownloadInputBoundary {

    private final DownloadDataAccessInterface downloader;
    private final DownloadOutputBoundary presenter;
    private final ProgressInputBoundary progressInteractor;


    public DownloadInteractor(DownloadDataAccessInterface downloader,
                              DownloadOutputBoundary presenter,
                              ProgressInputBoundary progressInteractor) {
        this.downloader = downloader;
        this.presenter = presenter;
        this.progressInteractor = progressInteractor;
    }


    @Override
    public void execute(DownloadInputData inputData) {

        String url = inputData.getUrl();
        String outputFolder = inputData.getOutputFolder();

        // Validation
        if (url == null || url.isEmpty()) {
            presenter.prepareFailView("URL cannot be empty.");
            return;
        }

        if (outputFolder == null || outputFolder.isEmpty()) {
            presenter.prepareFailView("Output folder cannot be empty.");
            return;
        }

        try {
            downloader.downloadVideo(url, outputFolder);

            DownloadOutputData outputData =
                    new DownloadOutputData("Download completed successfully.");

            presenter.prepareSuccessView(outputData);

        } catch (Exception e) {
            presenter.prepareFailView("Download failed: " + e.getMessage());
        }
    }
}
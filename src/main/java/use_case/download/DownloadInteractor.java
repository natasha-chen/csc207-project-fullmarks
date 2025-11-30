package use_case.download;

public class DownloadInteractor implements DownloadInputBoundary {

    private final DownloadDataAccessInterface downloader;
    private final DownloadOutputBoundary presenter;

    public DownloadInteractor(DownloadDataAccessInterface downloader,
                              DownloadOutputBoundary presenter) {
        this.downloader = downloader;
        this.presenter = presenter;
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
            downloader.download(url, outputFolder);

            DownloadOutputData outputData =
                    new DownloadOutputData("Download completed successfully.");

            presenter.prepareSuccessView(outputData);

        } catch (Exception e) {
            presenter.prepareFailView("Download failed: " + e.getMessage());
        }
    }
}

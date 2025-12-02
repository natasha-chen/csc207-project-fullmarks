package use_case.download;

public interface DownloadOutputBoundary {
    void prepareSuccessView(DownloadOutputData outputData);

    void prepareFailView(String errorMessage);

    void prepareProgressBar();
}
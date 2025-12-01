package use_case.download;

import use_case.progress.ProgressInputBoundary;

public interface DownloadDataAccessInterface {
    void downloadVideo(String url, String outputFolder, ProgressInputBoundary progressUpdater) throws Exception;
}
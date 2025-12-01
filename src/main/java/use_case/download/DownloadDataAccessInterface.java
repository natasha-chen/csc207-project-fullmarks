package use_case.download;

import use_case.progress.ProgressCallback;

public interface DownloadDataAccessInterface {
    void downloadVideo(String url, String outputFolder, ProgressCallback callback) throws Exception;
}
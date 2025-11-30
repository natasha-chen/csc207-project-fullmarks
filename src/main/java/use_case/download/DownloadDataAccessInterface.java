package use_case.download;

public interface DownloadDataAccessInterface {
    void downloadVideo(String url, String outputFolder) throws Exception;
}
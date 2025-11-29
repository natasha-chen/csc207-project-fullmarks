package use_case.download;

public interface DownloadDataAccessInterface {
    void download(String url, String outputFolder) throws Exception;
}
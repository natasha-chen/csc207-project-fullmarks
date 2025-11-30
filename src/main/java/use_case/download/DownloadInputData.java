package use_case.download;

public class DownloadInputData {

    private final String url;
    private final String outputFolder;

    public DownloadInputData(String url, String outputFolder) {
        this.url = url;
        this.outputFolder = outputFolder;
    }

    public String getUrl() {
        return url;
    }

    public String getOutputFolder() {
        return outputFolder;
    }
}

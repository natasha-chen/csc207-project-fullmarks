package use_case.download;

import java.util.List;

public class DownloadOutputData {

    private final String message;
    private final String url;

    public DownloadOutputData(String message, String url) {
        this.message = message;
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {return url;}
}
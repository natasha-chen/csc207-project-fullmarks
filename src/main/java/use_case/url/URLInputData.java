package use_case.url;

/**
 * Input Data for the URL validation use case.
 */
public class URLInputData {
    private final String url;

    public URLInputData(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
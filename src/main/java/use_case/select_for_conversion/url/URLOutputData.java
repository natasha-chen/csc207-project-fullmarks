package main.java.use_case.select_for_conversion.url;

/**
 * Output Data for the URL validation use case.
 */
public class URLOutputData {
    private final String url;
    private final boolean success;

    public URLOutputData(String url, boolean success) {
        this.url = url;
        this.success = success;
    }

    public String getUrl() {
        return url;
    }

    public boolean isSuccess() {
        return success;
    }
}

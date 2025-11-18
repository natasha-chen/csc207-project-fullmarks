package interface_adapter.url;

public class URLState {
    private String url;

    public URLState(String url) {
        this.url = url;
    }

    public URLState(URLState urlState) {
        url = urlState.getUrl();

    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}

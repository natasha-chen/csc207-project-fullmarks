package interface_adapter.url;

/**
 * The state for the URL View Model.
 */
public class URLState {
    private String username = "";
    private String url = "";
    private String error = null;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}
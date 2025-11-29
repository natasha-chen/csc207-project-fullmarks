package interface_adapter.failed_url;

/**
 * State for the Failed URL view.
 */
public class FailedURLState {
    private String errorMessage = "";

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

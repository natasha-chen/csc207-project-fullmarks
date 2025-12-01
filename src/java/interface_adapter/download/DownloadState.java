package interface_adapter.download;

public class DownloadState {

    private String url = "";
    private String outputFolder = "";
    private String statusMessage = "";
    private boolean success = false;
    private boolean inProgress = false;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOutputFolder() {
        return outputFolder;
    }

    public void setOutputFolder(String outputFolder) {
        this.outputFolder = outputFolder;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public boolean isSuccess() {return this.success;}

    public void setSuccess(boolean success) {this.success = success;}
}
package interface_adapter.download;

import interface_adapter.ViewModel;

public class DownloadViewModel extends ViewModel {

    public static final String VIEW_NAME = "download";

    private DownloadState state = new DownloadState();

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

    public DownloadState getState() {
        return state;
    }

    public void setState(DownloadState state) {
        this.state = state;
    }
}

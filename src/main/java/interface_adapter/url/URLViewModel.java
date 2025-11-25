package interface_adapter.url;

import interface_adapter.ViewModel;

public class URLViewModel extends ViewModel {

    public static final String VIEW_NAME = "url";

    private URLState state = new URLState();

    public URLViewModel() {}

    public URLState getState() {
        return state;
    }

    public void setState(URLState state) {
        this.state = state;
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }
}

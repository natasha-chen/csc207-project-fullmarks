package interface_adapter.url;

import interface_adapter.ViewModel;

public class URLViewModel extends ViewModel {
    public static final String TITLE_LABEL = "URL Input View";
    public static final String URL_LABEL = "Enter URL:";
    public static final String ENTER_BUTTON_LABEL = "Enter";
    public static final String BACK_BUTTON_LABEL = "Back";

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

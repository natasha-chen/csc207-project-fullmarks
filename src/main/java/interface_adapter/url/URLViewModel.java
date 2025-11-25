package main.java.interface_adapter.url;

import interface_adapter.ViewModel;

/**
 * The View Model for the URL View.
 */
public class URLViewModel extends ViewModel<URLState> {
    public static final String TITLE_LABEL = "URL Input View";
    public static final String URL_LABEL = "Enter URL:";
    public static final String ENTER_BUTTON_LABEL = "Enter";

    public URLViewModel() {
        super("url");
        setState(new URLState());
    }
}

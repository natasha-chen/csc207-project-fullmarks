package interface_adapter.url;

import interface_adapter.ViewModel;
import view.URLView;

/**
 * The View Model for the URL View.
 */
public class URLViewModel extends ViewModel<URLState> {
    public URLViewModel() {
        super("url");
        setState(new URLState());
    }
}

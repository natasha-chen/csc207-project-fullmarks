package interface_adapter.failed_url;

import interface_adapter.ViewModel;
import interface_adapter.url.URLState;

/**
 * View Model for the Failed URL view.
 */
public class FailedURLViewModel extends ViewModel {

    public static final String VIEW_NAME = "failed url";

    private FailedURLState state = new FailedURLState();

    public FailedURLViewModel() {}

    public FailedURLState getState() {
        return state;
    }

    public void setState(FailedURLState state) {
        this.state = state;
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }
}
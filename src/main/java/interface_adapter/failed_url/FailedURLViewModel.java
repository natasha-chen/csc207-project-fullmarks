
package interface_adapter.failed_url;

import interface_adapter.ViewModel;

/**
 * View Model for the Failed URL view.
 */
public class FailedURLViewModel extends ViewModel<FailedURLState> {
    public static final String TITLE_LABEL = "URL Validation Failed";
    public static final String TRY_AGAIN_BUTTON_LABEL = "Try Again";

    public FailedURLViewModel() {
        super("failed url");
        setActiveView(new FailedURLState());
    }
}
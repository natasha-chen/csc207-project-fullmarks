package interface_adapter.signup;

import interface_adapter.ViewModel;

/**
 * View model for the SignupView.
 *
 * <p>Stores the current SignupState and notifies observers when the state
 * changes. This class enables the UI to update automatically when the
 * presenter modifies the signup state.
 */
public class SignupViewModel extends ViewModel {

    public static final String VIEW_NAME = "signup";

    private SignupState state = new SignupState();
    /**
     * Constructs a SignupViewModel with the name of the view it represents.
     */
    public SignupViewModel() {}

    public SignupState getState() {
        return state;
    }

    public void setState(SignupState state) {
        this.state = state;
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }
}

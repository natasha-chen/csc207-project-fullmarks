package interface_adapter.signup;

import interface_adapter.ViewModel;

public class SignupViewModel extends ViewModel {

    public static final String VIEW_NAME = "signup";

    private SignupState state = new SignupState();

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

package interface_adapter.login;

import interface_adapter.ViewModel;

public class LoginViewModel extends ViewModel {

    public static final String VIEW_NAME = "login";

    private LoginState state = new LoginState();

    public LoginViewModel() {}

    public LoginState getState() {
        return state;
    }

    public void setState(LoginState state) {
        this.state = state;
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }
}

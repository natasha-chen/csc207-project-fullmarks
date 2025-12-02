package interface_adapter.login;

import interface_adapter.ViewModel;

/**
 * View model for the Login view.
 *
 * <p>Stores the state of the login screen, including the entered
 * username and password, as well as any error messages produced
 * during the login process.
 */
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

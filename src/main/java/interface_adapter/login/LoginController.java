package interface_adapter.login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

/**
 * Controller for the login use case.
 *
 * <p>Receives user input from the LoginView and sends it to the
 * LoginInteractor through the input boundary.
 */
public class LoginController {

    private final LoginInputBoundary loginInputBoundary;

    public LoginController(LoginInputBoundary loginInputBoundary) {
        this.loginInputBoundary = loginInputBoundary;
    }

    public void execute(String username, String password) {
        LoginInputData data = new LoginInputData(username, password);
        loginInputBoundary.execute(data);
    }
}
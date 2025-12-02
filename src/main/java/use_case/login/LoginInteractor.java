package use_case.login;

import entity.User;
import entity.PasswordHasher;
import use_case.user.UserDataAccessInterface;

/**
 * Interactor for the login use case.
 *
 * <p>Validates the user's credentials, retrieves user data,
 * and produces either a success or failure response.
 */
public class LoginInteractor implements LoginInputBoundary {

    private final UserDataAccessInterface userDataAccess;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(UserDataAccessInterface userDataAccess,
                           LoginOutputBoundary loginPresenter) {
        this.userDataAccess = userDataAccess;
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void execute(LoginInputData inputData) {

        String username = inputData.getUsername();
        String pw = inputData.getPassword();

        User user = userDataAccess.getUser(username);

        // no such user
        if (user == null) {
            loginPresenter.prepareFailView("Username not found.", username);
            return;
        }

        // wrong password (compare hashed)
        String hashedInput = PasswordHasher.hash(pw);

        if (!hashedInput.equals(user.getPassword())) {
            loginPresenter.prepareFailView("Incorrect password.", username);
            return;
        }

        // success
        LoginOutputData output = new LoginOutputData(username);
        loginPresenter.prepareSuccessView(output);
    }
}

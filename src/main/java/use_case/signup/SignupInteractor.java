package use_case.signup;

import entity.PasswordHasher;
import entity.User;
import use_case.user.UserDataAccessInterface;

/**
 * Interactor for the Signup use case.
 *
 * <p>This interactor handles the core business logic for creating a new user
 * account. It validates the input, checks whether the username already exists,
 * hashes the password, saves the new user through the data access interface,
 * and finally informs the presenter of success or failure.
 */
public class SignupInteractor implements SignupInputBoundary {

    private final UserDataAccessInterface userDataAccess;
    private final SignupOutputBoundary signupPresenter;

    /**
     * Constructs a SignupInteractor with the required data access and presenter.
     *
     * @param userDataAccess the data access interface used to check and save users
     * @param signupPresenter the output boundary used to prepare success or failure views
     */
    public SignupInteractor(UserDataAccessInterface userDataAccess,
                            SignupOutputBoundary signupPresenter) {
        this.userDataAccess = userDataAccess;
        this.signupPresenter = signupPresenter;
    }

    /**
     * Executes the Signup use case by validating user input, checking for username
     * availability, creating a new User entity, and informing the presenter of the
     * final result.
     *
     * <p>Failure cases:
     * <ul>
     *     <li>Passwords do not match</li>
     *     <li>Username already exists</li>
     * </ul>
     *
     * <p>On success, the new user is saved and output data is passed to the presenter.
     *
     * @param inputData the username and password information entered by the user
     */
    @Override
    public void execute(SignupInputData inputData) {

        String username = inputData.getUsername();
        String pw = inputData.getPassword();
        String pw2 = inputData.getRepeatPassword();

        // 1. password mismatch
        if (!pw.equals(pw2)) {
            signupPresenter.prepareFailView("Passwords do not match.", username);
            return;
        }

        // 2. username exists
        if (userDataAccess.getUser(username) != null) {
            signupPresenter.prepareFailView("Username already exists.", username);
            return;
        }

        // 3. create user
        String hashed = PasswordHasher.hash(pw);
        User user = new User(username, hashed);
        userDataAccess.saveUser(user);

        // 4. success
        SignupOutputData outputData = new SignupOutputData(username, false);
        signupPresenter.prepareSuccessView(outputData);

    }
}

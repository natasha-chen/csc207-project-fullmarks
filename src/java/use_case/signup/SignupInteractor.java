package use_case.signup;

import entity.PasswordHasher;
import entity.User;
import use_case.user.UserDataAccessInterface;


public class SignupInteractor implements SignupInputBoundary {

    private final UserDataAccessInterface userDataAccess;
    private final SignupOutputBoundary signupPresenter;

    public SignupInteractor(UserDataAccessInterface userDataAccess,
                            SignupOutputBoundary signupPresenter) {
        this.userDataAccess = userDataAccess;
        this.signupPresenter = signupPresenter;
    }

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

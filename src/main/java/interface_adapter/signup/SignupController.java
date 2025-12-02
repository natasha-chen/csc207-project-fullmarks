package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;
/**
 * Controller for handling user signup input.
 *
 * <p>Collects data from the view and passes it to the SignupInteractor.
 */
public class SignupController {

    private final SignupInputBoundary signupInputBoundary;

    public SignupController(SignupInputBoundary signupInputBoundary) {
        this.signupInputBoundary = signupInputBoundary;
    }

    public void execute(String username, String password, String repeatPassword) {
        SignupInputData data = new SignupInputData(username, password, repeatPassword);
        signupInputBoundary.execute(data);
    }
}

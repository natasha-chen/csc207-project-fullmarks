package use_case.signup;

/**
 * Input boundary for the Signup use case.
 *
 * <p>Defines the method that the controller calls to initiate the account
 * creation process.
 */
public interface SignupInputBoundary {
    /**
     * Executes the signup use case using the provided input data.
     *
     * @param signupInputData the information entered by the user to create an account
     */
    void execute(SignupInputData inputData);
}

package use_case.signup;

/**
 * Output boundary for the Signup use case.
 *
 * <p>Specifies how the presenter should handle success or failure messages
 * coming from the interactor.
 */
public interface SignupOutputBoundary {
    /**
     * Prepares the success view for account creation.
     *
     * @param outputData the data to display to the user after successful signup
     */
    void prepareSuccessView(SignupOutputData outputData);
    /**
     * Prepares the failure view when account creation fails.
     *
     * @param errorMessage the reason why signup failed
     */
    void prepareFailView(String errorMessage, String username);
}

package use_case.login;

/**
 * Output boundary for the login use case.
 *
 * <p>Defines how the presenter must respond to success or failure of login.
 */
public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData outputData);
    void prepareFailView(String errorMessage, String username);
}

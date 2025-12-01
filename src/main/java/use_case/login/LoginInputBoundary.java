package use_case.login;

/**
 * Input boundary for the login use case.
 *
 * <p>Defines the method that the controller must call to trigger the login process.
 */
public interface LoginInputBoundary {
    void execute(LoginInputData inputData);
}

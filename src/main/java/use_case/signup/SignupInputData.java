package use_case.signup;

/**
 * Data structure containing the input data required for user signup.
 *
 * <p>Holds the username and password that the user enters when attempting
 * to create a new account.
 */
public class SignupInputData {
    private final String username;
    private final String password;
    private final String repeatPassword;

    /**
     * Constructs a SignupInputData object with the given username and password.
     *
     * @param username the username chosen by the user
     * @param password the password chosen by the user
     */
    public SignupInputData(String username, String password, String repeatPassword) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRepeatPassword() { return repeatPassword; }
}

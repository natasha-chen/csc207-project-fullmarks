package use_case.login;

/**
 * Data structure representing the output data for a successful login.
 *
 * <p>Contains any information that the presenter may display after a user
 * successfully logs in.
 */
public class LoginOutputData {
    private final String username;

    public LoginOutputData(String username) {
        this.username = username;
    }

    public String getUsername() { return username; }
}

package use_case.login;

/**
 * Data structure representing the input data for the Login use case.
 *
 * <p>Stores the username and password entered by the user before the login
 * validation logic is executed.
 */
public class LoginInputData {
    private final String username;
    private final String password;

    public LoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}

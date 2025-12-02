package interface_adapter.login;

/**
 * Represents the state of the LoginView.
 *
 * <p>This includes the username, password, and error messages
 * entered or generated during the login process.
 */
public class LoginState {
    private String username = "";
    private String password = "";
    private String error;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}

package interface_adapter.signup;

/**
 * State object for the SignupView.
 *
 * <p>This class stores the username and any error messages associated with
 * the signup process. It is used by the SignupViewModel to represent what
 * should currently be displayed in the UI.
 */
public class SignupState {
    private String username = "";
    private String password = "";
    private String repeatPassword = "";
    private String error;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRepeatPassword() { return repeatPassword; }
    public void setRepeatPassword(String repeatPassword) { this.repeatPassword = repeatPassword; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}

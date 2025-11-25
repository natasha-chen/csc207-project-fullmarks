package interface_adapter.login;

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

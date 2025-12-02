package use_case.signup;

/**
 * Data structure representing the output data for a successful signup.
 *
 * <p>Contains information that may be shown to the user after a new account
 * has been successfully created.
 */
public class SignupOutputData {
    private final String username;
    private final boolean useCaseFailed;


    /**
     * Constructs a SignupOutputData object containing the resulting username.
     *
     * @param username the username of the newly created account
     */
    public SignupOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }
    /**
     * Returns the username of the newly created account.
     *
     * @return the username
     */
    public String getUsername() { return username; }
    public boolean isUseCaseFailed() { return useCaseFailed; }
}

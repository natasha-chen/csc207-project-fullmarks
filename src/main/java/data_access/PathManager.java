package data_access;

/**
 * A utility class responsible for managing file system paths used by the application.
 *
 * <p>This class stores the username of the currently logged-in user and generates
 * user-specific default download directories. These paths are constructed relative
 * to the user's operating system home directory, ensuring that downloaded files
 * are saved to a consistent and accessible location (e.g., Desktop/<username>/).
 *
 * <p>The download directory is created automatically if it does not already exist.
 */
public class PathManager {

    private static String loggedInUsername = "default";

    public static void setLoggedInUsername(String username) {
        loggedInUsername = username;
    }

    public static String getDefaultDownloadFolder() {
        String home = System.getProperty("user.home");
        return home + "/Desktop/" + loggedInUsername + "/";
    }
}

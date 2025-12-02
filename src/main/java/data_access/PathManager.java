package data_access;

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

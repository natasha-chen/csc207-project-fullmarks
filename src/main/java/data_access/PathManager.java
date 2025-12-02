package data_access;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    private static String currentUsername = null;

    public static void setLoggedInUsername(String username) {
        loggedInUsername = username;
    }

    public static String getDefaultDownloadFolder() {
        String home = System.getProperty("user.home");
        return home + File.separator + "Desktop" + File.separator + loggedInUsername + File.separator;
    }

    public static void setCurrentUsername(String username) {
        currentUsername = username;
    }

    public static String getCurrentUsername() {
        return currentUsername;
    }

    // ---- appdata helpers ----

    public static Path getUserAppDataRoot() {
        return Paths.get("appdata", loggedInUsername);
    }

    public static Path getUserMediaFolder() {
        return getUserAppDataRoot().resolve("media");
    }

    public static Path getUserPlaylistsJsonPath() {
        return getUserAppDataRoot().resolve("playlists.json");
    }

    public static void initUserAppData() {
        Path userRoot = getUserAppDataRoot();
        Path media = getUserMediaFolder();
        Path playlistsJson = getUserPlaylistsJsonPath();

        try {
            Files.createDirectories(media);
            Files.createDirectories(userRoot);

            if (!Files.exists(playlistsJson)) {
                Files.writeString(playlistsJson, "{}");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize user appdata", e);
        }
    }
}
package data_access;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathManager {

    private static final String DEFAULT_FOLDER_NAME = "FullMarksDownloads";

    public static String getDefaultDownloadFolder() {
        String home = System.getProperty("user.home");
        String fullPath = home + "/" + DEFAULT_FOLDER_NAME + "/";

        try {
            Path path = Paths.get(fullPath);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (Exception ignored) {}

        return fullPath;
    }
}

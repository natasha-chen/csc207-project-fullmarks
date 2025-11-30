package entity;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MediaPathManager {

    private static final Path APP_DATA = Paths.get("appdata");
    private static final Path MEDIA = APP_DATA.resolve("media");

    public static Path getAppDataPath() {
        return APP_DATA;
    }

    public static Path getMediaFolderPath() {
        return MEDIA;
    }
}
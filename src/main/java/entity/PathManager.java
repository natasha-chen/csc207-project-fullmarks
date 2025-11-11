package main;

public class PathManager {

    public static String getOutputFolder() {
        String outputFolder;

        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                outputFolder = System.getenv("USERPROFILE") + "\\Desktop\\mp4_video\\";
            } else if (os.contains("mac")) {
                outputFolder = System.getProperty("user.home") + "/Desktop/mp4_video/";
            } else {
                outputFolder = System.getProperty("user.home") + "/Desktop/mp4_video/";
            }

        } catch (Exception e) {
            outputFolder = System.getProperty("user.dir") + "/mp4_video/";
        }

        new java.io.File(outputFolder).mkdirs();
        return outputFolder;
    }
}

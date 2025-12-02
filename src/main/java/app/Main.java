package app;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        // Determine OS
        String os = System.getProperty("os.name").toLowerCase();
        boolean isWindows = os.contains("win");

        // Base command names (for PATH)
        String ytDlpCmd = isWindows ? "yt-dlp.exe" : "yt-dlp";
        String ffmpegCmd = isWindows ? "ffmpeg.exe" : "ffmpeg";

        // Prefer local bin/ copies if they exist (useful for distribution/marking)
        File localYtDlp = new File("bin" + File.separator + ytDlpCmd);
        File localFfmpeg = new File("bin" + File.separator + ffmpegCmd);


        if (localYtDlp.exists()) {
            ytDlpCmd = localYtDlp.getAbsolutePath();
        }
        if (localFfmpeg.exists()) {
            ffmpegCmd = localFfmpeg.getAbsolutePath();
        }

// Optional: sanity-check that the commands are runnable
        try {
            new ProcessBuilder(ytDlpCmd, "--version").start();
            new ProcessBuilder(ffmpegCmd, "-version").start();
        } catch (Exception e) {
            throw new RuntimeException(
                    "yt-dlp or ffmpeg not found. " +
                            "Either put them in ./bin or install them so they are on PATH.",
                    e
            );
        }


//        // Determine OS
//        String os = System.getProperty("os.name").toLowerCase();
//        boolean isWindows = os.contains("win");
//
//        // Build paths to binaries
//        String ytDlpPath = "bin" + File.separator + (isWindows ? "yt-dlp.exe" : "yt-dlp");
//        String ffmpegPath = "bin" + File.separator + (isWindows ? "ffmpeg.exe" : "ffmpeg");
//
//        // Ensure executables are accessible
//        File ytDlpFile = new File(ytDlpPath);
//        File ffmpegFile = new File(ffmpegPath);
//
//        if (!ytDlpFile.exists() || !ffmpegFile.exists()) {
//            throw new RuntimeException(String.format("%s or %s not exist", ytDlpPath, ffmpegPath));
//        }
//
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception ignored) {}

        app.AppBuilder builder = new app.AppBuilder();
        JPanel mainPanel = builder.build();

        JFrame frame = new JFrame("Youtube to MP3/4");
        frame.setContentPane(mainPanel);
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        builder.viewManagerModel.setActiveView("signup_login_menu");
        builder.viewManagerModel.firePropertyChanged();
        // to test the playlist library view
//        builder.viewManagerModel.setActiveView("playlist_library");
//        builder.viewManagerModel.firePropertyChanged();

    }
}

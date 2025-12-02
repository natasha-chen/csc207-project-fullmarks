package app;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        // Determine OS
        String os = System.getProperty("os.name").toLowerCase();
        boolean isWindows = os.contains("win");

        // Build paths to binaries
        String ytDlpPath = "bin" + File.separator + (isWindows ? "yt-dlp.exe" : "yt-dlp");
        String ffmpegPath = "bin" + File.separator + (isWindows ? "ffmpeg.exe" : "ffmpeg");

        // Ensure executables are accessible
        File ytDlpFile = new File(ytDlpPath);
        File ffmpegFile = new File(ffmpegPath);

        if (!ytDlpFile.exists() || !ffmpegFile.exists()) {
            throw new RuntimeException(String.format("%s or %s not exist", ytDlpPath, ffmpegPath));
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        AppBuilder builder = new AppBuilder();
        JPanel mainPanel = builder.build();

        JFrame frame = new JFrame("Youtube to MP3/4");
        frame.setContentPane(mainPanel);
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        builder.viewManagerModel.setActiveView("signup_login_menu");
        builder.viewManagerModel.firePropertyChanged();
    }
}
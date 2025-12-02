package data_access;

import use_case.download.DownloadDataAccessInterface;
import use_case.progress.ProgressCallback;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Downloader implements DownloadDataAccessInterface {

    public void downloadVideo(String url, String outputFolder, ProgressCallback progressCallback) {
        System.out.println("Starting download...");

        String os =  System.getProperty("os.name").toLowerCase();
        boolean isWindows = os.contains("win");
        String ytDlpPath = "bin/" + (isWindows ? "yt-dlp.exe" : "yt-dlp");

        ProcessBuilder pb = new ProcessBuilder(
                ytDlpPath,
                "-f", "mp4",
                "-o", outputFolder + "%(title)s.%(ext)s",
                url
        );
        pb.redirectErrorStream(true);

        try {
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                // Look for progress lines
                if (line.contains("[download]")) {

                    // Find percentage using regex
                    Matcher m = Pattern.compile("(\\d+\\.\\d+)%").matcher(line);
                    if (m.find()) {
                        double percent = Double.parseDouble(m.group(1));
                        progressCallback.reportProgress((int) percent, "Downloading");
                    }
                }
            }
            progressCallback.reportProgress(100, "Finished");

            process.waitFor();
            System.out.println("✅ Video downloaded successfully!");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

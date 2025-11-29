package data_access;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Downloader {

    public void downloadVideo(String url, String outputFolder) {
        System.out.println("Starting download...");

        ProcessBuilder pb = new ProcessBuilder(
                "yt-dlp",
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
                if (line.contains("%")) {
                    System.out.println(line);
                }
            }

            process.waitFor();
            System.out.println("✅ Video downloaded successfully!");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

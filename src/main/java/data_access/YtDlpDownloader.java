package data_access;

import use_case.download.DownloadDataAccessInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class YtDlpDownloader implements DownloadDataAccessInterface {

    @Override
    public void download(String url, String outputFolder) throws Exception {
        System.out.println("Starting download...");

        ProcessBuilder pb = new ProcessBuilder(
                "yt-dlp",
                "-f", "mp4",
                "-o", outputFolder + "/%(title)s.%(ext)s",
                url
        );
        pb.redirectErrorStream(true);

        try {
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("%")) {
                    System.out.println(line);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new Exception("Download failed, exit code: " + exitCode);
            }

            System.out.println("✅ Video downloaded successfully!");

        } catch (IOException | InterruptedException e) {
            throw new Exception("Download error: " + e.getMessage());
        }
    }
}

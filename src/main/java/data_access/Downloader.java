package data_access;

import use_case.download.DownloadDataAccessInterface;
import use_case.progress.ProgressInputBoundary;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Downloader implements DownloadDataAccessInterface {

    @Override
    public void downloadVideo(String url,
                         String outputFolder,
                         ProgressInputBoundary progressUpdater) throws Exception {

        ProcessBuilder pb = new ProcessBuilder(
                "yt-dlp",
                "-f", "mp4",
                "-o", outputFolder + "%(title)s.%(ext)s",
                url
        );

        pb.redirectErrorStream(true);
        Process process = pb.start();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;

        while ((line = reader.readLine()) != null) {

            // handle cancel
            if (progressUpdater.isCancelled()) {
                process.destroy();
                progressUpdater.reportProgress(0, "Download cancelled.");
                return;
            }

            // parse yt-dlp percentage
            if (line.contains("[download]")) {
                int percentIndex = line.indexOf('%');
                if (percentIndex > 12) {
                    String number = line.substring(percentIndex - 6, percentIndex).trim();

                    try {
                        double percent = Double.parseDouble(number);
                        progressUpdater.reportProgress(
                                (int) percent,
                                "Downloading... " + percent + "%"
                        );
                    } catch (NumberFormatException ignored) {}
                }
            }

            // Completed
            if (line.contains("100%")) {
                progressUpdater.reportProgress(100, "Download completed!");
            }
        }

        process.waitFor();
        progressUpdater.reportProgress(100, "Download completed!");
    }
}

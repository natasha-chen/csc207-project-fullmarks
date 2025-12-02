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
                "--newline",
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

            if (progressUpdater.isCancelled()) {
                process.destroy();
                progressUpdater.reportProgress(0, "Download cancelled.");
                return;
            }

            if (line.contains("[download]") && line.contains("%")) {
                try {
                    String percentStr = line.replaceAll(".*?([0-9.]+)%.*", "$1");
                    double percent = Double.parseDouble(percentStr);

                    progressUpdater.reportProgress(
                            (int) percent,
                            "Downloading... " + percent + "%"
                    );

                } catch (Exception ignored) {}
            }
        }

        process.waitFor();
        progressUpdater.reportProgress(100, "Download completed!");
    }
}

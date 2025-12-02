package data_access;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class AudioConverter {

    public void convertToMp3(String folder, String title) {
        String destination = "src" + File.separator + "appdata" + File.separator + "media" + File.separator;
        System.out.println("Converting to MP3...");
        String inputPath = folder + title + ".mp4";
        inputPath = inputPath.replace("|", "");
        String outputPath = destination + title + ".mp3";
        outputPath = outputPath.replace("|","");

        String os = System.getProperty("os.name").toLowerCase();
        boolean isWindows = os.contains("win");

        String ffmpegPath = "bin" + File.separator + (isWindows ? "ffmpeg.exe" : "ffmpeg");

        ProcessBuilder pb = new ProcessBuilder(
                ffmpegPath,
                "-i", inputPath,

                outputPath
        );
        pb.redirectErrorStream(true);

        try {
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            process.waitFor();
            System.out.println("✅ Conversion completed: " + outputPath);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

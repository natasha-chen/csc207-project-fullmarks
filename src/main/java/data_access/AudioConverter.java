package data_access;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class AudioConverter {

    //TODO; change; input: cache, output: cache
    public void convertToMp3(String folder, String title) {
        System.out.println("Converting to MP3...");
        String inputPath = folder + File.separator + title + ".mp4";
        String outputPath = folder + File.separator + title + ".mp3";

        ProcessBuilder pb = new ProcessBuilder(
                "ffmpeg",
                "-i", inputPath,
                "-q:a", "0",
                "-map", "a",
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

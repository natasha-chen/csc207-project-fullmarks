package data_access;

import use_case.select_for_conversion.ConverterInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class AudioConverter implements ConverterInterface {

    @Override
    public void convertToMP3(String folder, String title, String username) {
        String destination = "appdata" + File.separator + username + File.separator + "media" + File.separator;
        ensureOutputDirectory(destination);
        System.out.println("Converting to MP3...");
        String inputPath = folder + title + ".mp4";
        String outputPath = destination + title + ".mp3";
        outputPath = outputPath.replace("|", "");

        String os = System.getProperty("os.name").toLowerCase();
        boolean isWindows = os.contains("win");

        // Decide command: prefer local bin/ copy on Windows, otherwise use system command
        String ffmpegName = isWindows ? "ffmpeg.exe" : "ffmpeg";
        File localFfmpeg = new File("bin" + File.separator + ffmpegName);
        String ffmpegPath = localFfmpeg.exists() ? localFfmpeg.getAbsolutePath() : ffmpegName;
//        String ffmpegPath = "bin" + File.separator + (isWindows ? "ffmpeg.exe" : "ffmpeg");

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

    public void ensureOutputDirectory(String outputPath) {
        Path path = Path.of(outputPath);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException("Unable to create output directory: " + outputPath, e);
        }
    }
}

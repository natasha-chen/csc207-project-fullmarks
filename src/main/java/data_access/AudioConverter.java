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
        // Ensure folder ends with separator
        if (!folder.endsWith(File.separator)) {
            folder = folder + File.separator;
        }

        // Where to save MP3
        String destinationDir =
                "appdata" + File.separator + username + File.separator + "media" + File.separator;
        ensureOutputDirectory(destinationDir);

        String inputPath = folder + title + ".mp4";
        String outputPath = destinationDir + title + ".mp3";
        outputPath = outputPath.replace("|", "");

        System.out.println("[AudioConverter] input  = " + inputPath);
        System.out.println("[AudioConverter] output = " + outputPath);

        String os = System.getProperty("os.name").toLowerCase();
        boolean isWindows = os.contains("win");

        // Prefer local bin\ffmpeg.exe on Windows, otherwise system ffmpeg
        String ffmpegName = isWindows ? "ffmpeg.exe" : "ffmpeg";
        File localFfmpeg = new File("bin" + File.separator + ffmpegName);
        String ffmpegCmd = localFfmpeg.exists() ? localFfmpeg.getAbsolutePath() : ffmpegName;

        ProcessBuilder pb = new ProcessBuilder(
                ffmpegCmd,
                "-y",          // overwrite if file exists
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
                System.out.println("[ffmpeg] " + line);
            }

            int exit = process.waitFor();
            if (exit == 0) {
                System.out.println("✅ Conversion completed: " + outputPath);
            } else {
                System.out.println("❌ ffmpeg exited with code " + exit);
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("❌ Error running ffmpeg");
            e.printStackTrace();
        }
    }

    private void ensureOutputDirectory(String outputPath) {
        Path path = Path.of(outputPath);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException("Unable to create output directory: " + outputPath, e);
        }
    }
}

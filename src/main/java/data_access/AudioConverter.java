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
    public void convertToMP3(String inputPath, String username) {
        String destinationDir =
                "appdata" + File.separator + username + File.separator + "media" + File.separator;
        ensureOutputDirectory(destinationDir);

        System.out.println("[AudioConverter] input  = " + inputPath);

        File inputFile = new File(inputPath);

        // If the guessed path doesn't exist, fall back to the only .mp4 file in the folder
        if (!inputFile.exists()) {
            System.out.println("[AudioConverter] File does NOT exist: " + inputPath);

            File dir = inputFile.getParentFile();
            if (dir != null && dir.isDirectory()) {
                File[] mp4Files = dir.listFiles(f ->
                        f.isFile() && f.getName().toLowerCase().endsWith(".mp4"));

                if (mp4Files != null && mp4Files.length == 1) {
                    inputFile = mp4Files[0];
                    inputPath = inputFile.getAbsolutePath();
                    System.out.println("[AudioConverter] Fallback to: " + inputPath);
                } else {
                    System.out.println("[AudioConverter] mp4 files in folder:");
                    if (mp4Files != null) {
                        for (File f : mp4Files) {
                            System.out.println("  " + f.getName());
                        }
                    }
                    // Can't decide which file to use – stop here
                    return;
                }
            } else {
                // No valid directory – stop here
                return;
            }
        }

        // Derive base name from the actual file name
        String baseName = inputFile.getName().replaceFirst("\\.[^.]+$", ""); // strip extension
        String outputPath = destinationDir + baseName + ".mp3";
        outputPath = outputPath.replace("|", "");

        System.out.println("[AudioConverter] output = " + outputPath);

        String os = System.getProperty("os.name").toLowerCase();
        boolean isWindows = os.contains("win");

        // First try local project bin/
        String ffmpegPath = "bin" + File.separator + (isWindows ? "ffmpeg.exe" : "ffmpeg");
        File ffmpegFile = new File(ffmpegPath);

        // If it doesn't exist, fall back to system ffmpeg on PATH
        if (!ffmpegFile.exists()) {
            ffmpegPath = isWindows ? "ffmpeg.exe" : "ffmpeg";
        }

        System.out.println("[AudioConverter] using ffmpeg at: " + ffmpegPath);

        ProcessBuilder pb = new ProcessBuilder(
                ffmpegPath,
                "-i", inputPath,
                outputPath
        );
        pb.redirectErrorStream(true);

        try {
            Process process = pb.start();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("[ffmpeg] " + line);
                }
            }

            int exit = process.waitFor();
            if (exit == 0) {
                System.out.println("✅ Conversion completed: " + outputPath);
            } else {
                System.out.println("❌ ffmpeg exited with code " + exit);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void ensureOutputDirectory(String dirPath) {
        try {
            Files.createDirectories(Path.of(dirPath));
        } catch (IOException e) {
            throw new RuntimeException("Unable to create output directory: " + dirPath, e);
        }
    }
}

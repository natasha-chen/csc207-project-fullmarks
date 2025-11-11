package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Converter {

    public static void main(String[] args) {
        String url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"; // input link
        String outputFolder;

        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                // Windows
                outputFolder = System.getenv("USERPROFILE") + "\\Desktop\\mp4_video\\";
            } else if (os.contains("mac")) {
                // macOS
                outputFolder = System.getProperty("user.home") + "/Desktop/mp4_video/";
            } else {
                // Linux or other Unix-like OS
                outputFolder = System.getProperty("user.home") + "/Desktop/mp4_video/";
            }

        } catch (Exception e) {
            // fallback if something goes wrong
            outputFolder = System.getProperty("user.dir") + "/mp4_video/";
        }

        System.out.println("Using output folder: " + outputFolder);

        // ensure folder exists
        new java.io.File(outputFolder).mkdirs();

        // downloading YouTube video as mp4 file
        downloadVideo(url, outputFolder);

        // convert to mp3
        String title = "Rick Astley - Never Gonna Give You Up (Official Video) (4K Remaster)";
        convertToMp3(outputFolder, title);
    }

    /** downloading mp4 video to */
    public static void downloadVideo(String url, String outputFolder) {
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
                if (line.contains("%")) {  // print process
                    System.out.println(line);
                }
            }

            process.waitFor();
            System.out.println("Video downloaded successfully!");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**convert mp4 to mp3 */
    public static void convertToMp3(String folder, String title) {
        System.out.println("Converting to MP3...");
        String inputPath = folder + title + ".mp4";
        String outputPath = folder + title + ".mp3";

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
            System.out.println("Conversion completed: " + outputPath);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

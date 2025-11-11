package main.java.entity;

public class Main {

    public static void main(String[] args) {
        String url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
        String outputFolder = PathManager.getOutputFolder();

        System.out.println("Using output folder: " + outputFolder);

        Downloader downloader = new Downloader();
        AudioConverter converter = new AudioConverter();

        downloader.downloadVideo(url, outputFolder);

        String title = "Rick Astley - Never Gonna Give You Up (Official Video) (4K Remaster)";
        converter.convertToMp3(outputFolder, title);
    }
}

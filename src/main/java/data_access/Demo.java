/** package data_access;

import use_case.progress.ProgressCallback;

public class Demo {

    public static void main(String[] args) {
        String url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
        String outputFolder = PathManager.getOutputFolder();
        ProgressCallback progressInteractor = new ProgressCallback() {
            @Override
            public void reportProgress(int percent, String message) {
                System.out.println(message + ": " + percent);
            }
        };

        System.out.println("Using output folder: " + outputFolder);

        Downloader downloader = new Downloader();
        AudioConverter converter = new AudioConverter();
        try {
            downloader.downloadVideo(url, outputFolder, progressInteractor);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        String title = "Rick Astley - Never Gonna Give You Up (Official Video) (4K Remaster)";
        converter.convertToMp3(outputFolder, title);
    }
}
**/
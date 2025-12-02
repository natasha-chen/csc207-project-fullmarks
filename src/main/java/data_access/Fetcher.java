package data_access;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Fetcher {

    public JSONObject fetchInfo(String url) throws Exception {

        String os =  System.getProperty("os.name").toLowerCase();
        boolean isWindows = os.contains("win");

        String ytDlpPath = "bin/" + (isWindows ? "yt-dlp.exe" : "yt-dlp");

        ProcessBuilder pb = new ProcessBuilder(
                ytDlpPath,
                "-J",               // dump JSON
                "--dump-single-json",
                url
        );
        pb.redirectErrorStream(true);

        Process process = pb.start();

        StringBuilder jsonBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String line;
            boolean jsonStarted = false;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                // detect JSON start
                if (!jsonStarted && line.startsWith("{")) {
                    jsonStarted = true;
                }

                if (jsonStarted) {
                    jsonBuilder.append(line);
                }
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("yt-dlp returned non-zero exit code: " + exitCode);
        }

        // Now jsonBuilder should contain a valid single JSON object
        return new JSONObject(jsonBuilder.toString());
    }

    public static void main(String[] args) throws Exception {
        Fetcher fetcher = new Fetcher();
        JSONObject info = fetcher.fetchInfo("https://www.youtube.com/watch?v=dQw4w9WgXcQ");


        System.out.println("Title: " + info.getString("title"));
    }
}



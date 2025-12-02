package data_access;

import org.json.JSONObject;
import use_case.url.FetcherInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Fetcher implements FetcherInterface {

    @Override
    public JSONObject fetchInfo(String url) throws Exception {

        String os = System.getProperty("os.name");
        boolean isWindows = os.contains("win");

        String ytDlpPath = "bin" + File.separator + (isWindows ? "yt-dlp.exe" : "yt-dlp");


        ProcessBuilder pb = new ProcessBuilder(
                ytDlpPath,
                "-J",               // dump JSON
                "--dump-single-json",
                url
        );
        pb.redirectErrorStream(true);

        Process process = pb.start();

        StringBuilder output = new StringBuilder();
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
                    output.append(line);
                }
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("yt-dlp returned non-zero exit code: " + exitCode);
        }

        // Parse JSON with org.json
        return new JSONObject(output.toString());
    }

    public static void main(String[] args) throws Exception {
        Fetcher fetcher = new Fetcher();
        JSONObject info = fetcher.fetchInfo("");

        System.out.println("Title: " + info.getString("title"));
        System.out.println("Duration: " + info.getDouble("duration"));
    }
}



package data_access;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Verifier {

    public JSONObject fetchInfo(String url) throws Exception {
        ProcessBuilder pb = new ProcessBuilder(
                "yt-dlp",
                "-J",               // dump JSON
                "--no-warnings",
                "--skip-download",
                url
        );
        pb.redirectErrorStream(true);

        Process process = pb.start();

        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
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
        Verifier fetcher = new Verifier();
        JSONObject info = fetcher.fetchInfo("");

        System.out.println("Title: " + info.getString("title"));
        System.out.println("Duration: " + info.getDouble("duration"));
    }
}



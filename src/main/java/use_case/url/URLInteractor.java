package use_case.url;

import data_access.Fetcher;
import org.json.JSONArray;
import org.json.JSONObject;

import custom_datatype.VideoData;
import java.util.ArrayList;

import java.util.regex.Pattern;

/**
 * Interactor for the URL validation use case.
 */
public class URLInteractor implements URLInputBoundary {
    private final URLOutputBoundary urlPresenter;

    // YouTube URL patterns
    private static final Pattern YOUTUBE_PATTERN = Pattern.compile(
            "^(https?://)?(www\\.)?(youtube\\.com|youtu\\.be)/.+$",
            Pattern.CASE_INSENSITIVE
    );

    public URLInteractor(URLOutputBoundary urlPresenter) {
        this.urlPresenter = urlPresenter;
    }

    @Override
    public void execute(URLInputData urlInputData) {
        final String url = urlInputData.getUrl();

        // Check if URL is empty
        if (url == null || url.trim().isEmpty()) {
            urlPresenter.prepareFailView("URL cannot be empty.");
            return;
        }

        // Validate if it's a YouTube URL
        if (!isValidYouTubeUrl(url)) {
            urlPresenter.prepareFailView("Invalid YouTube URL. Please enter a valid YouTube link.");
            return;
        }

        // Fetch media info
        Fetcher fetcher = new Fetcher();
        try {
            // Success case
            JSONObject info = fetcher.fetchInfo(url);
            ArrayList<VideoData> playlistData = new ArrayList<>();
            if (info.has("entries")) {
                JSONArray videos = info.getJSONArray("entries");
                for (int i = 0; i < videos.length(); i++) {
                    JSONObject video = videos.getJSONObject(i);
                    Integer id = i;
                    playlistData.add(new VideoData(id,
                            video.getString("webpage_url"),
                            video.getString("title")));
                }
            } else {
                playlistData.add(new VideoData(0, url, info.getString("title")));
            }
            final URLOutputData outputData = new URLOutputData(url, playlistData);
            urlPresenter.prepareSuccessView(outputData);
        }
        catch (Exception e) {
            // Fails to fetch info after url is validated
            urlPresenter.prepareFailView(e.getMessage());
        }
    }

    /**
     * Validates if the given URL is a valid YouTube URL.
     * @param url the URL to validate
     * @return true if valid YouTube URL, false otherwise
     */
    private boolean isValidYouTubeUrl(String url) {
        return YOUTUBE_PATTERN.matcher(url).matches();
    }
}
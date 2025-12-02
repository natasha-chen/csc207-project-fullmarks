package use_case.url;

import data_access.Verifier;

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
        //check if valid url
        try {
            Verifier verifier = new Verifier();
            verifier.fetchInfo(url);
        } catch (Exception e) {
            urlPresenter.prepareFailView("This YouTube video is deleted or unavailable.");
            return;
        }

        // Validate if it's a YouTube URL
        if (!isValidYouTubeUrl(url)) {
            urlPresenter.prepareFailView("Invalid YouTube URL. Please enter a valid YouTube link.");
            return;
        }

        // Success case
        final URLOutputData outputData = new URLOutputData(url, true);
        urlPresenter.prepareSuccessView(outputData);
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
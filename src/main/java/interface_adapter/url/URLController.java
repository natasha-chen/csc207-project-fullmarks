package main.java.interface_adapter.url;

import main.java.use_case.select_for_conversion.url.URLInputBoundary;
import main.java.use_case.select_for_conversion.url.URLInputData;

/**
 * The controller for the URL use case.
 */
public class URLController {
    private final URLInputBoundary urlInteractor;

    public URLController(URLInputBoundary urlInteractor) {
        this.urlInteractor = urlInteractor;
    }

    /**
     * Executes the URL validation use case.
     * @param url the URL to validate
     */
    public void execute(String url) {
        final URLInputData urlInputData = new URLInputData(url);
        urlInteractor.execute(urlInputData);
    }
}

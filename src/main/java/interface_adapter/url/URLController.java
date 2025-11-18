package interface_adapter.url;

import use_case.select_for_conversion.url.URLInputBoundary;
import use_case.select_for_conversion.url.URLInputData;

/**
 * The controller for the URL use case.
 */
public class URLController {

    private final URLInputBoundary urlUseCaseInteractor;

    public URLController(URLInputBoundary urlUseCaseInteractor) {
         this.urlUseCaseInteractor = urlUseCaseInteractor;
    }

    public void execute(String url) {
         final URLInputData urlInputData = new URLInputData(url);

         urlUseCaseInteractor.execute(urlInputData);
    }
}

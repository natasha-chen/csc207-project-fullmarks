package interface_adapter.url;

import interface_adapter.ViewManagerModel;
import interface_adapter.failed_url.FailedURLState;
import interface_adapter.failed_url.FailedURLViewModel;
import use_case.url.URLOutputBoundary;
import use_case.url.URLOutputData;

/**
 * The presenter for the URL use case.
 */
public class URLPresenter implements URLOutputBoundary {
    private final URLViewModel urlViewModel;
    private final FailedURLViewModel failedURLViewModel;
    private final ViewManagerModel viewManagerModel;

    public URLPresenter(URLViewModel urlViewModel,
                        FailedURLViewModel failedURLViewModel,
                        ViewManagerModel viewManagerModel) {
        this.urlViewModel = urlViewModel;
        this.failedURLViewModel = failedURLViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(URLOutputData outputData) {
        // Clear the URL input field
        final URLState urlState = urlViewModel.getState();
        urlState.setUrl("");
        urlState.setError("");
        this.urlViewModel.firePropertyChanged();

        // switch to the next view, placeholder to go to failed  view

        this.viewManagerModel.setActiveView(failedURLViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // Update the Failed URL view model with the error message
        final FailedURLState failedURLState = failedURLViewModel.getState();
        failedURLState.setErrorMessage(errorMessage);
        this.failedURLViewModel.firePropertyChanged();

        // Switch to the Failed URL view
        this.viewManagerModel.setActiveView(failedURLViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
package interface_adapter.url;

import interface_adapter.ViewManagerModel;
import interface_adapter.download.DownloadViewModel;
import use_case.url.URLOutputBoundary;
import use_case.url.URLOutputData;

/**
 * The presenter for the URL use case.
 */
public class URLPresenter implements URLOutputBoundary {
    private final URLViewModel urlViewModel;
    private final DownloadViewModel downloadViewModel;
    private final ViewManagerModel viewManagerModel;

    public URLPresenter(URLViewModel urlViewModel,
                        DownloadViewModel downloadViewModel,
                        ViewManagerModel viewManagerModel) {
        this.urlViewModel = urlViewModel;
        this.downloadViewModel = downloadViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(URLOutputData outputData) {
        final URLState urlState = urlViewModel.getState();
        this.urlViewModel.firePropertyChanged();

        // switch to the download view
        downloadViewModel.getState().setUrl(urlState.getUrl());
        this.viewManagerModel.setActiveView(downloadViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // Update its own state
        final URLState urlState = urlViewModel.getState();
        urlState.setError(errorMessage);
        this.urlViewModel.firePropertyChanged();
    }
}
package interface_adapter.url;

import interface_adapter.ViewManagerModel;
import interface_adapter.download.DownloadViewModel;
import interface_adapter.select_for_conversion.SelectForConversionViewModel;
import use_case.url.URLOutputBoundary;
import use_case.url.URLOutputData;

/**
 * The presenter for the URL use case.
 */
public class URLPresenter implements URLOutputBoundary {
    private final URLViewModel urlViewModel;
    private final DownloadViewModel downloadViewModel;
    private final SelectForConversionViewModel selectForConversionViewModel;
    private final ViewManagerModel viewManagerModel;

    public URLPresenter(URLViewModel urlViewModel,
                        DownloadViewModel downloadViewModel,
                        SelectForConversionViewModel selectForConversionViewModel,
                        ViewManagerModel viewManagerModel) {
        this.urlViewModel = urlViewModel;
        this.downloadViewModel = downloadViewModel;
        this.selectForConversionViewModel = selectForConversionViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(URLOutputData outputData) {
        final URLState urlState = urlViewModel.getState();
        this.urlViewModel.firePropertyChanged();

        this.selectForConversionViewModel.getState().setPlaylistData(outputData.getPlaylistData());
        this.selectForConversionViewModel.firePropertyChanged();

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
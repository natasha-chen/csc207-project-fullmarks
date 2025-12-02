package interface_adapter.download;

import interface_adapter.ViewManagerModel;
import use_case.download.DownloadOutputBoundary;
import use_case.download.DownloadOutputData;
import interface_adapter.ProgressBar.ProgressViewModel;

public class DownloadPresenter implements DownloadOutputBoundary {

    private final DownloadViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public DownloadPresenter(DownloadViewModel viewModel,
                             ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DownloadOutputData outputData) {
        DownloadState state = viewModel.getState();
        state.setStatusMessage(outputData.getMessage());
        state.setInProgress(false);
        state.setSuccess(true);

        viewModel.setState(state);
        viewModel.firePropertyChanged();

        // optional: stay on same view; or you could switch views here if you want
        // viewManagerModel.setActiveView("some_other_view");
        // viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        DownloadState state = viewModel.getState();
        state.setStatusMessage(errorMessage);
        state.setInProgress(false);
        state.setSuccess(false);

        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareProgressBar() {
        ProgressViewModel progressViewModel = new ProgressViewModel();
        viewManagerModel.setActiveView(progressViewModel.getViewName());
    }
}
package interface_adapter.download;

import interface_adapter.ProgressBar.ProgressState;
import interface_adapter.ViewManagerModel;
import interface_adapter.select_for_conversion.SelectForConversionState;
import interface_adapter.select_for_conversion.SelectForConversionViewModel;
import use_case.download.DownloadOutputBoundary;
import use_case.download.DownloadOutputData;
import interface_adapter.ProgressBar.ProgressViewModel;

import javax.swing.*;

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

        SelectForConversionViewModel selectForConversionViewModel = new SelectForConversionViewModel();
        selectForConversionViewModel.getState().setLatestFilePath(viewModel.getState().getOutputFolder());

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
        System.out.println("Preparing ProgressBar");
        SwingUtilities.invokeLater(() -> {
            ProgressViewModel progressViewModel = new ProgressViewModel();
            viewManagerModel.setActiveView(progressViewModel.getViewName());
        });
    }
}
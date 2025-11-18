package interface_adapter.ProgressBar;

import use_case.progress.ProgressOutputBoundary;
import use_case.progress.ProgressOutputData;

/**
 * Presenter for progress updates.
 * Converts ProgressOutputData into ProgressState stored in the ViewModel.
 */
public class ProgressPresenter implements ProgressOutputBoundary {

    private final ProgressViewModel viewModel;

    public ProgressPresenter(ProgressViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void updateProgress(double percent, String message) {
        ProgressState state = viewModel.getState();
        state.setPercent(percent);
        state.setMessage(message);

        viewModel.firePropertyChanged();
    }

    @Override
    public void complete(String message) {
        ProgressState state = viewModel.getState();
        state.setPercent(100.0);
        state.setMessage(message);
        state.setComplete(true);

        viewModel.firePropertyChanged();
    }

    @Override
    public void error(String message) {
        ProgressState state = viewModel.getState();
        state.setMessage(message);
        state.setError(true);

        viewModel.firePropertyChanged();
    }
}

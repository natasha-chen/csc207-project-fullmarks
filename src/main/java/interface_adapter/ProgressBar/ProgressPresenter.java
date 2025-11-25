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
    public void updateProgress(ProgressOutputData outputData) {
        ProgressState state = viewModel.getState();
        state.setPercent(outputData.getPercent());
        state.setMessage(outputData.getMessage());
        state.setComplete(outputData.isDone());
        state.setError(false);  // update progress bar

        viewModel.firePropertyChanged();
    }

    @Override
    public void complete(ProgressOutputData outputData) {
        ProgressState state = viewModel.getState();
        state.setPercent(100.0);
        state.setMessage(outputData.getMessage());
        state.setComplete(true);
        state.setError(false);

        viewModel.firePropertyChanged();
    }

    @Override
    public void error(ProgressOutputData outputData) {
        ProgressState state = viewModel.getState();
        state.setMessage(outputData.getMessage());
        state.setError(true);

        viewModel.firePropertyChanged();
    }
}

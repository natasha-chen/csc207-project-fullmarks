package interface_adapter.ProgressBar;

import usecase.ProgressOutputBoundary;
import view.ProgressView;

public class ProgressPresenter implements ProgressOutputBoundary  {
    private final ProgressView view;

    public ProgressPresenter(ProgressView view) {
        this.view = view;
    }

    @Override
    public void updateProgress(double percent, String message) {
        view.showProgress(percent, message);
    }

    @Override
    public void complete(String message) {
        view.showCompletion(message);
    }

    @Override
    public void error(String message) {
        view.showError(message);
    }
}

}

package use_case.progress;

/**
 * Interactor for the progress use case.
 * Handles cancellation requests and provides a mechanism
 * for reporting progress updates to the Presenter.
 */
public class ProgressInteractor implements ProgressInputBoundary {

    private final ProgressOutputBoundary progressOutputBoundary;

    /** True when the user has requested cancellation. */
    private boolean cancelled = false;

    /**
     * Constructs a ProgressInteractor with the given Output Boundary.
     *
     * @param progressOutputBoundary the presenter that handles progress updates
     */
    public ProgressInteractor(ProgressOutputBoundary progressOutputBoundary) {
        this.progressOutputBoundary = progressOutputBoundary;
    }

    /**
     * Cancels the ongoing long-running process.
     * After this method is invoked, the process should stop as soon as possible.
     */
    @Override
    public void cancel() {
        cancelled = true;

        // Immediately notify presenter that cancellation was requested.
        ProgressOutputData data = new ProgressOutputData(
                0,
                "Download has been cancelled.",
                true,
                true
        );

        progressOutputBoundary.updateProgress(data);
    }

    /**
     * Reports a progress update to the Presenter,
     * unless cancellation has occurred.
     *
     * @param percent the completion percentage (0–100)
     * @param message a status message to present
     */
    public void reportProgress(int percent, String message) {
        if (!cancelled) {
            ProgressOutputData data = new ProgressOutputData(
                    percent,
                    message,
                    percent >= 100,
                    false
            );
            progressOutputBoundary.updateProgress(data);
        }
    }

    /**
     * Returns whether the process has been cancelled.
     * Your download/convert loop can check this regularly.
     *
     * @return true if cancellation was requested
     */
    public boolean isCancelled() {
        return cancelled;
    }
}

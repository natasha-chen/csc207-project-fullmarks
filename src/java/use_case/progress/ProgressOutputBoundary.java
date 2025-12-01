package use_case.progress;

public interface ProgressOutputBoundary {

    /**
     * Called by the Interactor to report progress update.
     */
    void updateProgress(ProgressOutputData outputData);

    /**
     * Called when the long-running task is successfully completed.
     */
    void complete(ProgressOutputData outputData);

    /**
     * Called when an error occurs.
     */
    void error(ProgressOutputData outputData);

}


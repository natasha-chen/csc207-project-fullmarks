package use_case.progress;
/**
 * Input Boundary for the progress use case.
 * This interface defines the request to cancel the ongoing operation
 */
public interface ProgressInputBoundary {

    /**
     * Cancels the ongoing long-running process. After this executes, the process should be
     * stopped as soon as possible.
     */
    void cancel();
    }




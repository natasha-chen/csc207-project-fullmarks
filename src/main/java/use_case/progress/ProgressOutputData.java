package use_case.progress;

/**
 * The data structure containing progress information
 * for the ProgressOutputBoundary.
 */
public class ProgressOutputData {

    private final int percent;
    private final String message;
    private final boolean isDone;
    private final boolean isCancelled;

    public ProgressOutputData(int percent, String message,
                              boolean isDone, boolean isCancelled) {
        this.percent = percent;
        this.message = message;
        this.isDone = isDone;
        this.isCancelled = isCancelled;
    }

    public int getPercent() { return percent; }

    public String getMessage() { return message; }

    public boolean isDone() { return isDone; }

    public boolean isCancelled() { return isCancelled; }

}


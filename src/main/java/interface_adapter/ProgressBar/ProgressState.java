package interface_adapter.ProgressBar;

/**
 * The state for the progress bar View Model.
 */

public class ProgressState {
    private double percent = 0.0;
    private String message = "Ready";
    private boolean isError = false;
    private boolean isComplete = false;

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public boolean isComplete() {return isComplete;}

    public void setComplete(boolean complete) {isComplete = complete;}
}
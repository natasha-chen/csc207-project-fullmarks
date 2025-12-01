package use_case.progress;

public interface ProgressCallback {
    void reportProgress(int percent, String message);
}
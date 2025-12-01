package main.java.use_case.load_playlist;

public interface LoadPlaylistOutputBoundary {

    void prepareSuccessView(LoadPlaylistOutputData outputData);

    void prepareFailView(String errorMessage);
}
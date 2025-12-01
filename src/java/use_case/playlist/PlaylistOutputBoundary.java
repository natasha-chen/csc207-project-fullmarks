package use_case.playlist;

public interface PlaylistOutputBoundary {

    void prepareSuccessView(PlaylistOutputData outputData);

    void prepareFailView(String errorMessage);
}
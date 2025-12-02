package use_case.playlist_library;

public interface PlaylistLibraryOutputBoundary {

    void prepareSuccessView(PlaylistLibraryOutputData outputData);

    void prepareFailView(String errorMessage);
}

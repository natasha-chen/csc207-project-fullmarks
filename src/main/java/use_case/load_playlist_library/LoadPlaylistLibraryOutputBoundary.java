package main.java.use_case.load_playlist_library;

public interface LoadPlaylistLibraryOutputBoundary {

    void prepareSuccessView(LoadPlaylistLibraryOutputData outputData);

    void prepareFailView(String errorMessage);
}

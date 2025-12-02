package use_case.delete_playlist;

public interface DeletePlaylistOutputBoundary {
    void prepareSuccessView(DeletePlaylistOutputData data);
    void prepareFailView(String errorMessage);
}

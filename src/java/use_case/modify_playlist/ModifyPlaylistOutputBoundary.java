package use_case.modify_playlist;

public interface ModifyPlaylistOutputBoundary {

    void prepareSuccessView(ModifyPlaylistOutputData data);

    void prepareFailView(String errorMessage);
}

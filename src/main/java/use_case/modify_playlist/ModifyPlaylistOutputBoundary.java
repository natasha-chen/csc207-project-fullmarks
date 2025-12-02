package use_case.modify_playlist;

import use_case.modify_playlist.ModifyPlaylistOutputData;

public interface ModifyPlaylistOutputBoundary {
    void prepareSuccessView(ModifyPlaylistOutputData data);

    void prepareFailView(String errorMessage);
}

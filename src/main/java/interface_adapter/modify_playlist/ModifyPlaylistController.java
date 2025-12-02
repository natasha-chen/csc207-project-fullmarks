package interface_adapter.modify_playlist;

import use_case.modify_playlist.*;

public class ModifyPlaylistController {

    private final ModifyPlaylistInputBoundary interactor;

    public ModifyPlaylistController(ModifyPlaylistInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void addSong(String playlistName, String songId) {
        interactor.execute(new ModifyPlaylistInputData(playlistName, songId, "ADD"));
    }

    public void removeSong(String playlistName, String songId) {
        interactor.execute(new ModifyPlaylistInputData(playlistName, songId, "REMOVE"));
    }
}

package interface_adapter.song_selection;

import use_case.modify_playlist.ModifyPlaylistInputBoundary;

import java.util.List;

public class SongSelectionController {

    private final ModifyPlaylistInputBoundary modifyPlaylistInteractor;

    public SongSelectionController(ModifyPlaylistInputBoundary interactor) {
        this.modifyPlaylistInteractor = interactor;
    }

    public void addSongs(String playlistName, List<String> songIds) {
        modifyPlaylistInteractor.addSongs(playlistName, songIds);
    }
}

package interface_adapter.playlist_view;

import use_case.playlist.PlaylistInputBoundary;
import use_case.playlist.PlaylistInputData;

public class PlaylistController {
    private final PlaylistInputBoundary interactor;

    public PlaylistController(PlaylistInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String playlistName) {
        interactor.execute(new PlaylistInputData(playlistName));
    }
}
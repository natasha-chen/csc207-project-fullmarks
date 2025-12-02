package interface_adapter.create_playlist;

import use_case.create_playlist.CreatePlaylistInputBoundary;
import use_case.create_playlist.CreatePlaylistInputData;

/**
 * Controller invoked by the "Create Playlist" button.
 */
public class CreatePlaylistController {

    private final CreatePlaylistInputBoundary interactor;

    public CreatePlaylistController(CreatePlaylistInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void createPlaylist(String playlistName) {
        CreatePlaylistInputData data = new CreatePlaylistInputData(playlistName);
        interactor.execute(data);
    }
}

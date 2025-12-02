package interface_adapter.delete_playlist;

import use_case.delete_playlist.DeletePlaylistInputBoundary;
import use_case.delete_playlist.DeletePlaylistInputData;

public class DeletePlaylistController {

    private final DeletePlaylistInputBoundary interactor;

    public DeletePlaylistController(DeletePlaylistInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String playlistName) {
        interactor.execute(new DeletePlaylistInputData(playlistName));
    }
}

package main.java.interface_adapter.playlist_view;

import main.java.use_case.load_playlist.LoadPlaylistInputBoundary;
import main.java.use_case.load_playlist.LoadPlaylistInputData;
import main.java.use_case.modify_playlist.ModifyPlaylistInputBoundary;
import main.java.use_case.modify_playlist.ModifyPlaylistInputData;

/**
 * Controller for PlaylistView. Called by the Swing PlaylistView when
 * the user clicks buttons or double-clicks songs.
 *
 * This class glues the UI layer to the LoadPlaylist + ModifyPlaylist use cases.
 */
public class PlaylistController {

    private final LoadPlaylistInputBoundary loadPlaylistInteractor;
    private final ModifyPlaylistInputBoundary modifyPlaylistInteractor;

    /**
     * Identifier of the playlist whose screen this controller controls.
     * Adjust type to match your actual ID type (String, int, etc.).
     */
    private final String playlistId;

    public PlaylistController(LoadPlaylistInputBoundary loadPlaylistInteractor,
                              ModifyPlaylistInputBoundary modifyPlaylistInteractor,
                              String playlistId) {
        this.loadPlaylistInteractor = loadPlaylistInteractor;
        this.modifyPlaylistInteractor = modifyPlaylistInteractor;
        this.playlistId = playlistId;
    }

    /**
     * Called when the PlaylistView wants to (re)load the playlist data.
     * e.g., when the screen is shown.
     */
    public void loadPlaylist() {
        LoadPlaylistInputData inputData =
                new LoadPlaylistInputData(playlistId);
        loadPlaylistInteractor.execute(inputData);
    }

    /**
     * Called when user clicks "Delete" on the playlist view.
     * index may be -1 if nothing is selected; the interactor or presenter
     * can handle that as an error.
     */
    public void deleteSongAt(String playlistName, String songId) {
        modifyPlaylistController.removeSong(playlistName, songId);
    }

    /**
     * Called when user clicks "Add Song(s)".
     * Typically this doesn’t call the modify use case directly; instead it
     * opens the SongSelectionDialog, whose own controller will call
     * ModifyPlaylist with Action.ADD.
     *
     * Here we just leave a TODO hook for your view/navigation logic.
     */
    public void addSongs() {
        // TODO: open SongSelectionDialogView for this playlistId
    }

    /**
     * Called when user clicks "Library".
     * This usually tells a ViewManager to switch back to LibraryView.
     */
    public void goToLibrary() {
        // TODO: implement navigation back to LibraryView
    }

    /**
     * Called when user clicks "Play" (whole playlist).
     * You and your friend can wire this to the play use case / play UI.
     */
    public void playPlaylist() {
        // TODO: call your friend's PlayPlaylistController
    }

    /**
     * Called when user double-clicks a song row to play that specific song.
     */
    public void playSongAt(int index) {
        // TODO: call your friend's "play single song" use case / controller
    }
}

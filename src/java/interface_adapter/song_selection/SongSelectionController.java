package interface_adapter.song_selection;

import interface_adapter.modify_playlist.ModifyPlaylistController;

import java.util.List;

/**
 * Controller for the Song Selection UI.
 * Delegates to ModifyPlaylistController to add songs to a playlist.
 */

public class SongSelectionController {

    private final ModifyPlaylistController modifyPlaylistController;

    public SongSelectionController(ModifyPlaylistController modifyPlaylistController) {
        this.modifyPlaylistController = modifyPlaylistController;
    }

    /**
     * Add selected songs to the given playlist.
     */
    public void addSongs(String playlistName, List<String> songIds) {
        for (String songId : songIds) {
            modifyPlaylistController.addSong(playlistName, songId);
        }
    }
}

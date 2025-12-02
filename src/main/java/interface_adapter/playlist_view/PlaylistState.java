package interface_adapter.playlist_view;

import java.util.ArrayList;
import java.util.List;

/**
 * Plain state object for the Playlist screen.
 * You can store this inside PlaylistViewModel if you want to follow the
 * "ViewModel holds a State" pattern.
 */
public class PlaylistState {

    private String playlistName = "";
    private List<String> songNames = new ArrayList<>();
    private String errorMessage = "";

    public PlaylistState() {
    }

    public PlaylistState(PlaylistState other) {
        this.playlistName = other.playlistName;
        this.songNames = new ArrayList<>(other.songNames);
        this.errorMessage = other.errorMessage;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public List<String> getSongNames() {
        return songNames;
    }

    public void setSongNames(List<String> songNames) {
        this.songNames = songNames;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

package interface_adapter.playlist_view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * ViewModel for the Playlist screen.
 * Holds the playlist name, song list, and any error message.
 */
public class PlaylistViewModel {

    public static final String PLAYLIST_NAME_PROPERTY = "playlistName";
    public static final String SONG_NAMES_PROPERTY = "songNames";
    public static final String ERROR_MESSAGE_PROPERTY = "errorMessage";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private String playlistName = "";
    private List<String> songNames = new ArrayList<>();
    private String errorMessage = "";

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
        support.firePropertyChange(PLAYLIST_NAME_PROPERTY, null, this.playlistName);
    }

    public List<String> getSongNames() {
        return songNames;
    }

    public void setSongNames(List<String> songNames) {
        this.songNames = songNames;
        support.firePropertyChange(SONG_NAMES_PROPERTY, null, this.songNames);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        support.firePropertyChange(ERROR_MESSAGE_PROPERTY, null, this.errorMessage);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
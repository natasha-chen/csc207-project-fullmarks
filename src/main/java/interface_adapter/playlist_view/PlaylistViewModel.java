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

    // --- Getters ---

    public String getPlaylistName() {
        return playlistName;
    }

    public List<String> getSongNames() {
        return new ArrayList<>(songNames);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    // --- Setters that fire per-field change events ---

    public void setPlaylistName(String playlistName) {
        String old = this.playlistName;
        this.playlistName = playlistName;
        support.firePropertyChange(PLAYLIST_NAME_PROPERTY, old, this.playlistName);
    }

    public void setSongNames(List<String> songNames) {
        List<String> old = new ArrayList<>(this.songNames);
        this.songNames = new ArrayList<>(songNames);
        support.firePropertyChange(SONG_NAMES_PROPERTY, old, new ArrayList<>(this.songNames));
    }

    public void setErrorMessage(String errorMessage) {
        String old = this.errorMessage;
        this.errorMessage = errorMessage;
        support.firePropertyChange(ERROR_MESSAGE_PROPERTY, old, this.errorMessage);
    }

    // --- Listener registration ---

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    // --- Bulk notification used by presenters ---

    /**
     * Call this after making several updates at once (e.g., in a presenter)
     * to notify the view that all properties may have changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange(PLAYLIST_NAME_PROPERTY, null, this.playlistName);
        support.firePropertyChange(SONG_NAMES_PROPERTY, null, new ArrayList<>(this.songNames));
        support.firePropertyChange(ERROR_MESSAGE_PROPERTY, null, this.errorMessage);
    }
}
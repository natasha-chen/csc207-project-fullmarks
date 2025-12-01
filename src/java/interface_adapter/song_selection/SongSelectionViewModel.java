package interface_adapter.song_selection;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class SongSelectionViewModel {

    public static final String AVAILABLE_SONGS = "availableSongs";
    public static final String CLOSE = "close";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private List<String> availableSongs;
    private boolean closeRequested = false;

    public List<String> getAvailableSongs() {
        return availableSongs;
    }

    public void setAvailableSongs(List<String> songs) {
        this.availableSongs = songs;
        support.firePropertyChange(AVAILABLE_SONGS, null, songs);
    }

    public void requestClose() {
        this.closeRequested = true;
        support.firePropertyChange(CLOSE, false, true);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

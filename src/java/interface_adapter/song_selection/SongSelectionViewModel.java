package interface_adapter.song_selection;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class SongSelectionViewModel {

    public static final String AVAILABLE_SONGS = "availableSongs";
    public static final String CLOSE = "close";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private List<String> availableSongs = new ArrayList<>();
    private boolean closeRequested = false;

    public List<String> getAvailableSongs() {
        return new ArrayList<>(availableSongs);
    }

    public void setAvailableSongs(List<String> songs) {
        this.availableSongs = new ArrayList<>(songs);
        support.firePropertyChange(AVAILABLE_SONGS, null, new ArrayList<>(this.availableSongs));
    }

    public boolean isCloseRequested() {
        return closeRequested;
    }

    public void requestClose() {
        boolean old = this.closeRequested;
        this.closeRequested = true;
        support.firePropertyChange(CLOSE, old, this.closeRequested);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

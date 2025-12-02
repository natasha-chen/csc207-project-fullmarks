package interface_adapter.library;

import java.util.ArrayList;
import java.util.List;

public class LibraryState {

    private List<String> playlistNames = new ArrayList<>();
    private String errorMessage = "";

    public List<String> getPlaylistNames() {
        return playlistNames;
    }

    public void setPlaylistNames(List<String> playlistNames) {
        this.playlistNames = playlistNames;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

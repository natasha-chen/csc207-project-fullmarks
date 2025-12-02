package use_case.delete_playlist;

import java.util.List;

public class DeletePlaylistOutputData {
    private final List<String> playlistNames;

    public DeletePlaylistOutputData(List<String> playlistNames) {
        this.playlistNames = playlistNames;
    }

    public List<String> getPlaylistNames() {
        return playlistNames;
    }
}

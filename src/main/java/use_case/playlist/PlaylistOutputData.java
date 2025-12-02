package use_case.playlist;

import java.util.List;

public class PlaylistOutputData {
    private final String playlistName;
    private final List<String> songIds;
    private final List<String> songNames;

    public PlaylistOutputData(String playlistName, List<String> songIds, List<String> songNames) {
        this.playlistName = playlistName;
        this.songIds = songIds;
        this.songNames = songNames;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public List<String> getSongIds() {
        return songIds;
    }

    public List<String> getSongNames() {
        return songNames;
    }
}

package use_case.modify_playlist;

import java.util.List;

public class ModifyPlaylistOutputData {

    private final String playlistName;
    private final List<String> songIds;
    private final List<String> songNames;

    public ModifyPlaylistOutputData(String playlistName, List<String> songIds, List<String> songNames) {
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

    public List<String> getSongNames() { return songNames; }
}

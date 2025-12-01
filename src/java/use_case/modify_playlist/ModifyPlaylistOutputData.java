package use_case.modify_playlist;

import java.util.List;

public class ModifyPlaylistOutputData {

    private final String playlistName;
    private final List<String> songIds;

    public ModifyPlaylistOutputData(String playlistName, List<String> songIds) {
        this.playlistName = playlistName;
        this.songIds = songIds;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public List<String> getSongIds() {
        return songIds;
    }
}

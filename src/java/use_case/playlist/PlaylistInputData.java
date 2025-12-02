package use_case.playlist;

public class PlaylistInputData {
    private final String playlistName;

    public PlaylistInputData(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistName() {
        return playlistName;
    }
}

package use_case.delete_playlist;

public class DeletePlaylistInputData {
    private final String playlistName;

    public DeletePlaylistInputData(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistName() {
        return playlistName;
    }
}

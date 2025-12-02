package use_case.modify_playlist;

/**
 * Input data for the ModifyPlaylist use case.
 * operation: "ADD" or "REMOVE".
 */
public class ModifyPlaylistInputData {

    private final String playlistName;
    private final String songId;
    private final String operation;

    public ModifyPlaylistInputData(String playlistName, String songId, String operation) {
        this.playlistName = playlistName;
        this.songId = songId;
        this.operation = operation;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public String getSongId() {
        return songId;
    }

    public String getOperation() {
        return operation;
    }
}

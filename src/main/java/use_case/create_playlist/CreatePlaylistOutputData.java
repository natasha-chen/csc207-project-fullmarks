package use_case.create_playlist;

import java.util.List;

/**
 * Output data for creating a playlist.
 * Contains the new playlist name and the full updated list of playlist names.
 */
public class CreatePlaylistOutputData {

    private final String createdPlaylistName;
    private final List<String> allPlaylistNames;

    public CreatePlaylistOutputData(String createdPlaylistName,
                                    List<String> allPlaylistNames) {
        this.createdPlaylistName = createdPlaylistName;
        this.allPlaylistNames = List.copyOf(allPlaylistNames);
    }

    public String getCreatedPlaylistName() {
        return createdPlaylistName;
    }

    public List<String> getAllPlaylistNames() {
        return allPlaylistNames;
    }
}

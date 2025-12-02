package use_case.playlist_library;

import entity.Playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistLibraryOutputData {

    private final List<String> playlistNames;

    public PlaylistLibraryOutputData(List<Playlist> playlistObj) {
        playlistNames = new ArrayList<>();
        for (Playlist playlist : playlistObj) {
            playlistNames.add(playlist.getName());
        }
    }

    public List<String> getPlaylistNames() {
        return playlistNames;
    }
}

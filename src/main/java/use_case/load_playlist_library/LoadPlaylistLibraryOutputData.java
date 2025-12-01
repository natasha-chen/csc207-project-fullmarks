package main.java.use_case.load_playlist_library;

import java.util.List;

public class LoadPlaylistLibraryOutputData {

    private final List<String> playlistNames;

    public LoadPlaylistLibraryOutputData(List<String> playlistNames) {
        this.playlistNames = playlistNames;
    }

    public List<String> getPlaylistNames() {
        return playlistNames;
    }
}

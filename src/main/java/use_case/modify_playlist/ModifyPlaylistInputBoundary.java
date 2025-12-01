package main.java.use_case.modify_playlist;

import java.util.List;

public interface ModifyPlaylistInputBoundary {

    void addSongs(String playlistName, List<String> songIds);

    void removeSong(String playlistName, String songId);
}

package main.java.data_access;

import entity.Playlist;
import entity.MediaFile;

import java.util.List;

// *** come back to edit later

public interface PlaylistDataAccessInterface {

    // playlist operations
    List<Playlist> getAllPlaylists();
    // gets the name of the playlist, no two playlist has the same name
    Playlist getPlaylist(String name);
    // saves any edits made to a playlist, such as deleting or adding a song
    void savePlaylist(Playlist playlist);
    // deletes the playlist
    void deletePlaylist(String name);

    // updates the local mp3 info after conversion -- not sure if i need this anymore
    void updateMedia();
    MediaFile getMedia(String id);
    // fetches all the songs in main\appdata\media\
    List<MediaFile> getAllMedia();
}



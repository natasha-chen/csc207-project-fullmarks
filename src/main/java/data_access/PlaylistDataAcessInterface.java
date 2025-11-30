package data_access;

import entity.Playlist;
import entity.MediaFile;

import java.util.List;

// *** come back to edit later

public interface PlaylistDataAcessInterface {

    // playlist operations
    List<Playlist> getAllPlaylists();
    Playlist getPlaylist(String name);
    void savePlaylist(Playlist playlist);
    void deletePlaylist(String name);

    // media operations
    MediaFile getMedia(String id);
    void saveMedia(MediaFile media);
    List<MediaFile> getAllMedia();
}

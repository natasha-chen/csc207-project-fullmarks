package data_access;

import entity.MediaFile;
import entity.Playlist;
import entity.MediaPathManager;
import data_access.PlaylistDataAccessInterface;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class PlaylistDataAccess implements PlaylistDataAccessInterface {

    // Paths
    private final Path appDataRoot;
    private final Path mediaFolder;
    private final Path playlistsJsonPath;

    // Local storage: duplicate of JSON and media folder, updates simultaneously
    // based on savePlaylist and (tbd:) also saveMedia
    private final Map<String, Playlist> playlists = new HashMap<>();
    private final Map<String, MediaFile> media = new HashMap<>();

    public PlaylistDataAccess() {
        // the media folder
        this.mediaFolder = MediaPathManager.getMediaFolderPath();
         // the appdata folder
         this.appDataRoot = mediaFolder.getParent();
         // the playlist file under appdata folder
         this.playlistsJsonPath = appDataRoot.resolve("playlists.json");

        try {
            // create folders just in case they don't exist -- won't create if
            // they do exist
            Files.createDirectories(mediaFolder);
            Files.createDirectories(appDataRoot);
        } catch (IOException e) {
            throw new RuntimeException("Could not create appdata folders", e);
        }

        loadMediaFromFolder();   // stub for now
        loadPlaylistsFromJson(); // stub for now
    }

    // ===== PlaylistDataAccessInterface methods =====

    @Override
    public List<Playlist> getAllPlaylists() {
        return new ArrayList<>(playlists.values());
    }

    @Override
    public Playlist getPlaylist(String name) {
        return playlists.get(name);
    }

    @Override
    public void savePlaylist(Playlist playlist) {
        playlists.put(playlist.getName(), playlist);
        savePlaylistsToJson(); // write playlists map to playlists.json
    }

    @Override
    public void deletePlaylist(String name) {
        playlists.remove(name);
        savePlaylistsToJson();
    }

    @Override
    public MediaFile getMedia(String id) {
        return media.get(id);
    }

    @Override
    public List<MediaFile> getAllMedia() {
        return new ArrayList<>(media.values());
    }

    // ===== Internal helper methods =====

    private void loadMediaFromFolder() {
        // Example stub:
        // For each .mp3 file in mediaFolder, create a MediaFile and put in map.
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(mediaFolder, "*.mp3")) {
            for (Path p : stream) {
                String fileName = p.getFileName().toString();
                MediaFile m = new MediaFile(fileName, fileName); // title = fileName for now
                media.put(m.getFileID(), m);
            }
        } catch (IOException e) {
            // handle or log; do NOT crash the whole app if you can avoid it
        }
    }

    private void loadPlaylistsFromJson() {
        // TODO: read playlistsJsonPath if it exists,
        // parse JSON into Playlist objects, and fill `playlists` map.
        if (!Files.exists(playlistsJsonPath)) {
            return; // nothing yet, start with empty map
        }

        // read string, parse with org.json, etc. (you can add later)
    }

    private void savePlaylistsToJson() {
        // TODO: serialize `playlists` map to JSON and write to playlistsJsonPath.
        // You’ll likely use org.json.JSONObject / JSONArray for this.
    }
}

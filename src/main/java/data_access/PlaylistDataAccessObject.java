package data_access;

import entity.MediaFile;
import entity.Playlist;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class PlaylistDataAccessObject implements PlaylistDataAccessInterface {

    // Local storage: duplicate of JSON and media folder, per current user
    private final Map<String, Playlist> playlists = new HashMap<>();
    private final Map<String, MediaFile> media = new HashMap<>();

    public PlaylistDataAccessObject() {
        // No username here; paths come from PathManager.
        // LoginPresenter will call reloadForCurrentUser() after login.
    }

    // ===== Per-user reinitialization =====

    @Override
    public void reloadForCurrentUser() {
        playlists.clear();
        media.clear();

        Path appDataRoot = getAppDataRoot();
        Path mediaFolder = getMediaFolder();

        try {
            Files.createDirectories(appDataRoot);
            Files.createDirectories(mediaFolder);
        } catch (IOException e) {
            throw new RuntimeException("Could not create appdata folders", e);
        }

        loadMediaFromFolder();
        loadPlaylistsFromJson();
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
        savePlaylistsToJson();
    }

    @Override
    public boolean exists(String playlistName) {
        return playlists.containsKey(playlistName);
    }

    @Override
    public void deletePlaylist(String name) {
        playlists.remove(name);
        savePlaylistsToJson();
    }

    @Override
    public void updateMedia() {
        media.clear();
        loadMediaFromFolder();
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

    private Path getAppDataRoot() {
        return PathManager.getUserAppDataRoot();
    }

    private Path getMediaFolder() {
        return PathManager.getUserMediaFolder();
    }

    private Path getPlaylistsJsonPath() {
        return PathManager.getUserPlaylistsJsonPath();
    }

    private void loadMediaFromFolder() {
        Path mediaFolder = getMediaFolder();
        if (!Files.exists(mediaFolder)) {
            return;
        }

        try (DirectoryStream<Path> stream =
                     Files.newDirectoryStream(mediaFolder, "*.mp3")) {
            for (Path p : stream) {
                String fileName = p.getFileName().toString();
                // For now, use filename as title; you can change this later
                MediaFile m = new MediaFile(fileName, fileName);
                media.put(m.getFileID(), m);
            }
        } catch (IOException e) {
            // log or handle; don’t crash whole app
        }
    }

    private void loadPlaylistsFromJson() {
        Path playlistsJsonPath = getPlaylistsJsonPath();

        if (!Files.exists(playlistsJsonPath)) {
            return; // nothing yet, start with empty map
        }

        try {
            String raw = Files.readString(playlistsJsonPath);
            if (raw == null || raw.isBlank()) {
                return;
            }

            JSONObject root = new JSONObject(raw);

            for (String playlistName : root.keySet()) {
                JSONArray arr = root.getJSONArray(playlistName);

                Playlist p = new Playlist(playlistName);
                for (int i = 0; i < arr.length(); i++) {
                    String songId = arr.getString(i);
                    p.addSong(songId);
                }

                playlists.put(playlistName, p);
            }
        } catch (Exception e) {
            // If parsing fails, keep playlists empty for this user
            throw new RuntimeException("Failed to read playlists.json", e);
        }
    }

    private void savePlaylistsToJson() {
        Path playlistsJsonPath = getPlaylistsJsonPath();

        try {
            Files.createDirectories(playlistsJsonPath.getParent());

            JSONObject root = new JSONObject();
            for (Playlist p : playlists.values()) {
                JSONArray arr = new JSONArray(p.getAllSongId());
                root.put(p.getName(), arr);
            }

            Files.writeString(playlistsJsonPath, root.toString(2));
        } catch (IOException e) {
            throw new RuntimeException("Failed to write playlists.json", e);
        }
    }
}

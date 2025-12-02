package entity;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String name; // name of the playlist
    private final List<String> songIds; // ordered list of MediaFile.id
    private final List<String> songNames;

    public Playlist(String name) {
        this.name = name;
        this.songIds = new ArrayList<>();
        this.songNames = new ArrayList<>();
    }

    public String getName() { return name; }
    // note: all mp3 files are referred to by ID/filename, not the name given by user

    // returns the filename/ID of the songs in playlist
    public List<String> getAllSongId() { return new ArrayList<>(songIds); }

    public List<String> getAllSongNames() { return new ArrayList<>(songNames); }

    // adds newest song to end of list
    public void addSong(String mediaId) { songIds.add(mediaId); }

    // since every ID is different, this has no issue
    public void removeSong(String mediaId) { songIds.remove(mediaId); }

    }

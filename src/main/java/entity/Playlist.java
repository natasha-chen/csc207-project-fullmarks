package entity;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String name; // name of the playlist
    private final List<String> mediaIds; // ordered list of MediaFile.id

    public Playlist(String name) {
        this.name = name;
        this.mediaIds = new ArrayList<>();
    }

    public String getName() { return name; }
    public void rename(String newName) { this.name = newName; }

    // note: all mp3 files are referred to by ID/filename, not the name given by user

    // returns the filename/ID of the songs in playlist
    public List<String> getMediaIds() { return new ArrayList<>(mediaIds); }

    // adds newest song to end of list
    public void addMedia(String mediaId) { mediaIds.add(mediaId); }

    // since every ID is different, this has no issue
    public void removeMedia(String mediaId) { mediaIds.remove(mediaId); }

    // order of playlist correspond to their order in mediaIDs
    public void moveMedia(int fromIndex, int toIndex) {
        String id = mediaIds.remove(fromIndex);
        mediaIds.add(toIndex, id);
    }
}
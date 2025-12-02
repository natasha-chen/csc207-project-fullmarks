package use_case.url;

import custom_datatype.VideoData;

import java.util.List;

/**
 * Output Data for the URL validation use case.
 */
public class URLOutputData {
    private final String url;
    private final List<VideoData> playlistData;

    public URLOutputData(String url, List<VideoData> playlistData) {
        this.url = url;
        this.playlistData = playlistData;
    }

    public String getUrl() {
        return url;
    }

    public List<VideoData> getPlaylistData() {return playlistData;}
}
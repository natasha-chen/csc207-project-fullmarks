package interface_adapter.select_for_conversion;

import custom_datatype.VideoData;

import java.util.List;

/**
 * The state for the Select for Conversion View Model.
 */

public class SelectForConversionState {
    // private String username;
    private List<VideoData> playlistVideoData;
    private String errorMessage;

    // public String getUsername() {return username;}

    public List<VideoData> getPlaylistData() {return this.playlistVideoData;}

    public String getSelectForConversionError() {return this.errorMessage;}

    public void setPlaylistData(List<VideoData> playlistData) {this.playlistVideoData = playlistData;}

    public void setConversionError(String errorMessage) {this.errorMessage = errorMessage;}

    // public void setUsername(String username) {this.username = username;}

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("SelectForConversionState{" + '\'');
        for (VideoData videoData : this.playlistVideoData) {
            result.append(videoData.toString()).append('\'');
        }
        result.append('}');
        return result.toString();
    }
}

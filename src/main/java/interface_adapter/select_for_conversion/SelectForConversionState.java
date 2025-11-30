package interface_adapter.select_for_conversion;

import custom_datatype.VideoData;

import java.util.List;

/**
 * The state for the Select for Conversion View Model.
 */

public class SelectForConversionState {
    private List<VideoData> playlistVideoData;
    private String errorMessage;


    public List<VideoData> getPlaylistData() {return this.playlistVideoData;}

    public String getSelectForConversionError() {return this.errorMessage;}

    //TODO: either someone else converts playlist url into List<VideoData> or i do
    public void setPlaylistData(List<VideoData> playlistData) {this.playlistVideoData = playlistData;}

    public void setConversionError(String errorMessage) {this.errorMessage = errorMessage;}

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

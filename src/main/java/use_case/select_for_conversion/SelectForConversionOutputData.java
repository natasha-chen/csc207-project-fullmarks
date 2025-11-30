package select_for_conversion_input_boundary;

import custom_datatype.VideoData;

import java.util.List;

/**
 * Output Data for the Select for Conversion Use Case.
 */
public class SelectForConversionOutputData {
    public final List<VideoData> selectedVideos;

    public SelectForConversionOutputData(List<VideoData> videos) {
        this.selectedVideos = videos;
    }

    public List<VideoData> getSelectedVideos() {
        return selectedVideos;
    }
}

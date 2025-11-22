package use_case.select_for_conversion;

import custom_datatype.VideoData;

import java.util.List;

/**
 * Output Data for the Select for Conversion Use Case. In the case that
 * I do not handle conversions, I'll forward this to the Use Case that does.
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

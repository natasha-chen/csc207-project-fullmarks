package use_case.select_for_conversion;

import java.util.List;

/**
 * Output Data for the Select for Conversion Use Case. I don't know what the difference should be between Input Data
 * and Output Data. Perhaps include the URLs and titles of each video?
 */
public class SelectForConversionOutputData {
    public final List<String> validSelectedVideos;

    public SelectForConversionOutputData(List<String> videos) {
        this.validSelectedVideos = videos;
    }

    public List<String> getVideos() {
        return validSelectedVideos;
    }
}

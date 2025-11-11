package use_case.select_for_conversion;

import java.util.ArrayList;

/**
 * Output Data for the Select for Conversion Use Case.
 */
public class SelectForConversionOutputData {
    public final ArrayList<String> selectedVideos;

    public SelectForConversionOutputData(ArrayList<String> videos) {
        this.selectedVideos = videos;
    }

    public ArrayList<String> getVideos() {
        return selectedVideos;
    }
}

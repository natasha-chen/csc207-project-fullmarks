package use_case.select_for_conversion;

import java.util.ArrayList;

/**
 * The input data for the Select for Conversion Use Case.
 */
public class SelectForConversionInputData {


    private final ArrayList<String> playlist;
    public SelectForConversionInputData(ArrayList<String> playlist) {
        this.playlist = playlist;
    }

    public ArrayList<String> getPlaylist() {
        return playlist;
    }

}

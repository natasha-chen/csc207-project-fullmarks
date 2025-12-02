package use_case.select_for_conversion;

import custom_datatype.VideoData;

import java.util.List;

/**
 * The input data for the Select for Conversion Use Case.
 * For a valid playlist url, the Input Data is a list of class VideoData.
 * Each VideoData contains: String url, boolean mp3Bool and boolean mp4Bool.
 * This means that the user can download in both formats.
 * Aside from this list, the Input Data should also contain a String filePath.
 */
public class SelectForConversionInputData {

    private final List<VideoData> videoDataList;
    private final String inputFolder;

    /**
     * Build when given a filePath, and, for each video in the playlist:
     * url, mp3Bool
     */
    public SelectForConversionInputData(List<VideoData> videoDataList,
                                        String inputFolder) {
        this.videoDataList = videoDataList;
        this.inputFolder = inputFolder;
    }

    List<VideoData> getVideoDataList() {return videoDataList;}

    String getInputFolder() {return inputFolder;}
}

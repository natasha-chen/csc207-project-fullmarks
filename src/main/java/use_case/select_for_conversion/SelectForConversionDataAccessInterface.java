package use_case.select_for_conversion;

import custom_datatype.VideoData;

import java.util.List;

/**
 * The DAO interface for the Select for Conversion Use Case.
 */
public interface SelectForConversionDataAccessInterface {

    /**
     * Verifies the URL and classifies its destination.
     * // @param selectedVideos the String list of selected video urls.
     */
    /**
     * Call the Fetcher DAO to obtain information about the url's media.
     */
    List<VideoData> getVideoData();

    /**
     * Call an AudioConverter DAO to convert an MP4 file.
     * @param inputPath the path of the MP4 file
     * @param outputPath the path of the MP3's destination
     */
    void convertToMP3(String inputPath, String outputPath, String username);
}

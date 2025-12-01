package use_case.select_for_conversion;

import entity.Video;

import java.util.List;

/**
 * The DAO interface for the Select for Conversion Use Case.
 */
public interface SelectForConversionDataAccessInterface {

    /**
     * Verifies the URL and classifies its destination.
     * @param selectedVideos the String list of selected video urls.
     */
    Video verifyURL(List<String> selectedVideos);

    void download(String url, boolean mp3Bool, String path);
}

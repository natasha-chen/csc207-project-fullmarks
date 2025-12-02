package use_case.select_for_conversion;

import custom_datatype.VideoData;
import data_access.AudioConverter;

import java.util.List;
import java.util.ArrayList;


/**
 * The Select for Conversion Interactor.
 * Given a list of bools, mutate
 */
public class SelectForConversionInteractor implements SelectForConversionInputBoundary {
    private final SelectForConversionOutputBoundary selectForConversionPresenter;

    public SelectForConversionInteractor(SelectForConversionOutputBoundary selectForConversionOutputBoundary) {
        this.selectForConversionPresenter = selectForConversionOutputBoundary;
    }

    /**
     * Current responsibility: execute conversion to MP3 if any mp3Bools are True
     * otherwise, send error pop-up
     * @param selectForConversionInputData the input data for this use case
     */
    @Override
    public SelectForConversionOutputData execute(SelectForConversionInputData selectForConversionInputData) {
        if (selectForConversionInputData.getVideoDataList() == null ||
                selectForConversionInputData.getVideoDataList().isEmpty()) {
            selectForConversionPresenter.prepareFailView("Empty playlist.");
            selectForConversionPresenter.switchToMenuView();
            return new SelectForConversionOutputData(new ArrayList<>());
        }
        List<VideoData> playlist = selectForConversionInputData.getVideoDataList();
        String inputFolder = selectForConversionInputData.getInputFolder();
        String username = selectForConversionInputData.getUsername();
        boolean anyMp3Bool = playlist.stream().anyMatch(VideoData::isMP3Bool);
        List<VideoData> newList = new ArrayList<>();
        if (anyMp3Bool) {
            for (VideoData videoData : playlist) {
                if  (videoData.isMP3Bool())
                {
                    AudioConverter converter = new AudioConverter();
                    converter.convertToMP3(inputFolder, videoData.getTitle(), username);
                    newList.add(videoData);
                }
            }
            selectForConversionPresenter.prepareSuccessView(new SelectForConversionOutputData(newList));
        }
        else  {
            // No mp3Bools triggers fail view
            selectForConversionPresenter.prepareFailView("No videos selected for conversion.");
        }
        return new SelectForConversionOutputData(newList);
    }


    @Override
    public void switchToMenuView() {
        selectForConversionPresenter.switchToMenuView();
    }
}

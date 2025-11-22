package use_case.select_for_conversion;

import custom_datatype.VideoData;
import data_access.*;

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
    public void execute(SelectForConversionInputData selectForConversionInputData) {
        List<VideoData> playlist = selectForConversionInputData.getVideoDataList();
        boolean anyMp3Bool = playlist.stream().anyMatch(VideoData::getMp3Bool);
        if (anyMp3Bool) {
            System.out.println("TODO: I either convert the video (modify InputData to have folderPath)");
            System.out.println("or I execute a Use Case that converts it");
            List<VideoData> newList = new ArrayList<>();
            for (VideoData videoData : playlist) {
                if  (videoData.getMp3Bool()) {newList.add(videoData);}
            }
            selectForConversionPresenter.prepareSuccessView(new SelectForConversionOutputData(newList));
        }
        else  {
            // No mp3Bools triggers fail view
            selectForConversionPresenter.prepareFailView("No videos selected for conversion.");
        }
    }


    @Override
    public void switchToUrlView() {
        selectForConversionPresenter.switchToUrlView();
    }
}

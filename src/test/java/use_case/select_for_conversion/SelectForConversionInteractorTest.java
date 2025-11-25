package use_case.select_for_conversion;

import custom_datatype.VideoData;

import java.util.ArrayList;

import entity.VideoFactory;
import entity.Video;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectForConversionInteractorTest {

    /**
     * Test representing a valid playlist with at least one video chosen to be converted.
     */
    @Test
    void testValidPlaylistData() {
        // Initializing test InputData

        // Initializing test Presenter
        ArrayList<Video> expected = getValidExpectedVideos();
        SelectForConversionInputBoundary interactor = getInteractor();
        SelectForConversionOutputData outputData = interactor.execute(getValidInputData());

        int i = 0;
        for (VideoData video : outputData.getSelectedVideos()) {
            if (video.getMp3Bool()) {
                assertEquals(expected.get(i).getTitle(), video.getTitle());
                assertEquals(expected.get(i++).getUrl(), video.getUrl());
            }
        }
    }

    @NotNull
    private static SelectForConversionInputData getValidInputData() {
        VideoData video1 = new VideoData(1, "https://www.youtube.com/watch?v=2XR1Cw-ysDg",
                "Regalia");
        VideoData video2 = new VideoData(2, "https://www.youtube.com/watch?v=PYfsEBhSpFk",
                "Reason");
        VideoData video3 = new VideoData(3, "https://www.youtube.com/watch?v=zLJkI4mEudY",
                "Moon Roar");
        ArrayList<VideoData> newList = new ArrayList<>();
        newList.add(video1);
        video1.setMp3Bool(true);
        newList.add(video2);
        video2.setMp3Bool(false);
        newList.add(video3);
        video3.setMp3Bool(true);
        return new SelectForConversionInputData(newList);
    }

    @NotNull
    private static ArrayList<Video> getValidExpectedVideos() {
        VideoFactory videoFactory = new VideoFactory();
        Video expectedVideo1 = videoFactory.create("https://www.youtube.com/watch?v=2XR1Cw-ysDg",
                "Regalia", 305.0);
        // video 2 is skipped since mp3Bool of the test counterpart remains false
        Video expectedVideo3 = videoFactory.create("https://www.youtube.com/watch?v=zLJkI4mEudY",
                "Moon Roar", 306.0);
        ArrayList<Video> expected = new ArrayList<>();
        expected.add(expectedVideo1);
        expected.add(expectedVideo3);
        return expected;
    }

    /**
     * Test representing a valid playlist with no videos chosen to be converted.
     */
    @Test
    void testNoSelection() {
        SelectForConversionInputBoundary interactor = getInteractor();
        SelectForConversionOutputData outputData = interactor.execute(getUnselectedInputData());
        assertEquals(0, outputData.getSelectedVideos().size());
    }

    @NotNull
    private static SelectForConversionInputData getUnselectedInputData() {
        VideoData video1 = new VideoData(1, "https://www.youtube.com/watch?v=2XR1Cw-ysDg",
                "Regalia");
        VideoData video2 = new VideoData(2, "https://www.youtube.com/watch?v=PYfsEBhSpFk",
                "Reason");
        VideoData video3 = new VideoData(3, "https://www.youtube.com/watch?v=zLJkI4mEudY",
                "Moon Roar");
        ArrayList<VideoData> newList = new ArrayList<>();
        newList.add(video1);
        video1.setMp3Bool(false);
        newList.add(video2);
        video2.setMp3Bool(false);
        newList.add(video3);
        video3.setMp3Bool(false);
        return new SelectForConversionInputData(newList);
    }

    /**
     * It is possible to input a valid empty playlist URL. This test simulates such case.
     */
    @Test
    void testEmptyPlaylistData() {
        SelectForConversionInputBoundary interactor = getInteractor();
        SelectForConversionOutputData outputData = interactor.execute(
                new SelectForConversionInputData(new ArrayList<>()));
        assertEquals(0, outputData.getSelectedVideos().size());
    }

//    @Test
//    void testNonexistentFolderPath() {
//
//    }
//    @Test
//    void testUnwriteableFolderPath() {
//
//    }
//    @Test
//    void testInaccessibleVideo() {
//
//    }
//    @Test
//    void testNetworkFailure() {
//
//    }
//    @Test
//    void testLargePlaylistData() {
//
//    }
//    @Test
//    void testCancelButton() {
//
//    }

    @NotNull
    private static SelectForConversionInputBoundary getInteractor() {
        SelectForConversionOutputBoundary presenter = new SelectForConversionOutputBoundary() {
            @Override
            public void prepareSuccessView(SelectForConversionOutputData outputData) {
                System.out.println("Test prepareSuccessView triggered.");
            }
            @Override
            public void prepareFailView(String errorMessage) {
                System.out.println("Error: " + errorMessage);
            }
            @Override
            public void switchToUrlView() {
                System.out.println("Test switchToUrlView triggered.");
            }
        };

        return new SelectForConversionInteractor(presenter);
    }
}

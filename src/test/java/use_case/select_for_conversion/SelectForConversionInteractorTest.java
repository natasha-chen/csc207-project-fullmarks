package use_case.select_for_conversion;

import custom_datatype.VideoData;

import java.util.ArrayList;

import entity.VideoFactory;
import entity.Video;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectForConversionInteractorTest {
    // Given
    @Test
    void testValidPlaylistData() {
        // Initializing test InputData
        SelectForConversionInputData inputData = getSelectForConversionInputData();

        // Initializing test Presenter
        VideoFactory videoFactory = new VideoFactory();
        Video expectedVideo1 = videoFactory.create("https://www.youtube.com/watch?v=2XR1Cw-ysDg",
                "Regalia", 305.0);
        // video 2 is skipped since mp3Bool of the test counterpart remains false
        Video expectedVideo3 = videoFactory.create("https://www.youtube.com/watch?v=zLJkI4mEudY",
                "Moon Roar", 306.0);
        ArrayList<Video> expected = new ArrayList<>();
        expected.add(expectedVideo1);
        expected.add(expectedVideo3);
        SelectForConversionInputBoundary interactor = getSelectForConversionInputBoundary();
        SelectForConversionOutputData outputData = interactor.execute(inputData);
        assertEquals(expectedVideo1.getTitle(), outputData.getSelectedVideos().get(0).getTitle());
        assertEquals(expectedVideo3.getTitle(), outputData.getSelectedVideos().get(1).getTitle());
    }

    @NotNull
    private static SelectForConversionInputBoundary getSelectForConversionInputBoundary() {
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

    @NotNull
    private static SelectForConversionInputData getSelectForConversionInputData() {
        VideoData video1 = new VideoData(1, "https://www.youtube.com/watch?v=2XR1Cw-ysDg",
                "Regalia");
        VideoData video2 = new VideoData(2, "https://www.youtube.com/watch?v=PYfsEBhSpFk",
                "Reason");
        VideoData video3 = new VideoData(3, "https://www.youtube.com/watch?v=zLJkI4mEudY",
                "Moon Roar");
        ArrayList<VideoData> newList = new ArrayList<>();
        newList.add(video1); video1.setMp3Bool(true);
        newList.add(video2); video2.setMp3Bool(false);
        newList.add(video3); video3.setMp3Bool(true);
        return new SelectForConversionInputData(newList);
    }

//    @Test
//    void testEmptyPlaylistData() {
//
//    }
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
}

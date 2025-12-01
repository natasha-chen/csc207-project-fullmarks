package use_case.URLTest;

import org.junit.jupiter.api.Test;
import use_case.url.URLInputData;
import use_case.url.URLInteractor;
import use_case.url.URLOutputBoundary;
import use_case.url.URLOutputData;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for URLInteractor
 */
class URLInteractorTest {

    /**
     * Test that a valid standard YouTube URL is accepted.
     */
    @Test
    void testValidYouTubeUrl() {
        // Arrange
        TestURLPresenter presenter = new TestURLPresenter();
        URLInteractor interactor = new URLInteractor(presenter);
        URLInputData inputData = new URLInputData("https://www.youtube.com/watch?v=dQw4w9WgXcQ");

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(presenter.isSuccessCalled(), "Success view should be called");
        assertFalse(presenter.isFailCalled(), "Fail view should not be called");
        assertEquals("https://www.youtube.com/watch?v=dQw4w9WgXcQ", presenter.getOutputData().getUrl());
        assertTrue(presenter.getOutputData().isSuccess());
    }

    /**
     * Test that a valid YouTube short URL (youtu.be) is accepted.
     */
    @Test
    void testValidYouTubeUrl_ShortForm() {
        // Arrange
        TestURLPresenter presenter = new TestURLPresenter();
        URLInteractor interactor = new URLInteractor(presenter);
        URLInputData inputData = new URLInputData("https://youtu.be/dQw4w9WgXcQ");

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(presenter.isSuccessCalled());
        assertFalse(presenter.isFailCalled());
        assertEquals("https://youtu.be/dQw4w9WgXcQ", presenter.getOutputData().getUrl());
    }

    /**
     * Test that a YouTube URL without www is accepted.
     */
    @Test
    void testValidYouTubeUrl_WithoutWWW() {
        // Arrange
        TestURLPresenter presenter = new TestURLPresenter();
        URLInteractor interactor = new URLInteractor(presenter);
        URLInputData inputData = new URLInputData("https://youtube.com/watch?v=dQw4w9WgXcQ");

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(presenter.isSuccessCalled());
        assertFalse(presenter.isFailCalled());
    }

    /**
     * Test that a YouTube URL with http (not https) is accepted.
     */
    @Test
    void testValidYouTubeUrl_HTTP() {
        // Arrange
        TestURLPresenter presenter = new TestURLPresenter();
        URLInteractor interactor = new URLInteractor(presenter);
        URLInputData inputData = new URLInputData("http://www.youtube.com/watch?v=dQw4w9WgXcQ");

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(presenter.isSuccessCalled());
        assertFalse(presenter.isFailCalled());
    }

    /**
     * Test that a YouTube playlist URL is accepted.
     */
    @Test
    void testValidYouTubeUrl_Playlist() {
        // Arrange
        TestURLPresenter presenter = new TestURLPresenter();
        URLInteractor interactor = new URLInteractor(presenter);
        URLInputData inputData = new URLInputData("https://www.youtube.com/playlist?list=PLrAXtmErZgOeiKm4sgNOknGvNjby9efdf");

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(presenter.isSuccessCalled());
        assertFalse(presenter.isFailCalled());
    }

    /**
     * Test that a YouTube channel URL is accepted.
     */
    @Test
    void testValidYouTubeUrl_Channel() {
        // Arrange
        TestURLPresenter presenter = new TestURLPresenter();
        URLInteractor interactor = new URLInteractor(presenter);
        URLInputData inputData = new URLInputData("https://www.youtube.com/channel/UCX6OQ3DkcsbYNE6H8uQQuVA");

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(presenter.isSuccessCalled());
        assertFalse(presenter.isFailCalled());
    }

    /**
     * Test that mixed case YouTube URL is accepted (case insensitive).
     */
    @Test
    void testValidYouTubeUrl_MixedCase() {
        // Arrange
        TestURLPresenter presenter = new TestURLPresenter();
        URLInteractor interactor = new URLInteractor(presenter);
        URLInputData inputData = new URLInputData("https://www.YouTube.COM/watch?v=dQw4w9WgXcQ");

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(presenter.isSuccessCalled());
        assertFalse(presenter.isFailCalled());
    }

    /**
     * Test that null URL triggers fail view with appropriate error message.
     */
    @Test
    void testNullUrl() {
        // Arrange
        TestURLPresenter presenter = new TestURLPresenter();
        URLInteractor interactor = new URLInteractor(presenter);
        URLInputData inputData = new URLInputData(null);

        // Act
        interactor.execute(inputData);

        // Assert
        assertFalse(presenter.isSuccessCalled());
        assertTrue(presenter.isFailCalled());
        assertEquals("URL cannot be empty.", presenter.getErrorMessage());
    }

    /**
     * Test that empty URL triggers fail view with appropriate error message.
     */
    @Test
    void testEmptyUrl() {
        // Arrange
        TestURLPresenter presenter = new TestURLPresenter();
        URLInteractor interactor = new URLInteractor(presenter);
        URLInputData inputData = new URLInputData("");

        // Act
        interactor.execute(inputData);

        // Assert
        assertFalse(presenter.isSuccessCalled());
        assertTrue(presenter.isFailCalled());
        assertEquals("URL cannot be empty.", presenter.getErrorMessage());
    }

    /**
     * Test that whitespace-only URL triggers fail view with appropriate error message.
     */
    @Test
    void testWhitespaceOnlyUrl() {
        // Arrange
        TestURLPresenter presenter = new TestURLPresenter();
        URLInteractor interactor = new URLInteractor(presenter);
        URLInputData inputData = new URLInputData("   ");

        // Act
        interactor.execute(inputData);

        // Assert
        assertFalse(presenter.isSuccessCalled());
        assertTrue(presenter.isFailCalled());
        assertEquals("URL cannot be empty.", presenter.getErrorMessage());
    }

    /**
     * Test that a non-YouTube URL (Google) triggers fail view.
     */
    @Test
    void testInvalidUrl_Google() {
        // Arrange
        TestURLPresenter presenter = new TestURLPresenter();
        URLInteractor interactor = new URLInteractor(presenter);
        URLInputData inputData = new URLInputData("https://www.google.com");

        // Act
        interactor.execute(inputData);

        // Assert
        assertFalse(presenter.isSuccessCalled());
        assertTrue(presenter.isFailCalled());
        assertEquals("Invalid YouTube URL. Please enter a valid YouTube link.", presenter.getErrorMessage());
    }


    /**
     * Test that a malformed URL triggers fail view.
     */
    @Test
    void testInvalidUrl_Malformed() {
        // Arrange
        TestURLPresenter presenter = new TestURLPresenter();
        URLInteractor interactor = new URLInteractor(presenter);
        URLInputData inputData = new URLInputData("not a url at all");

        // Act
        interactor.execute(inputData);

        // Assert
        assertFalse(presenter.isSuccessCalled());
        assertTrue(presenter.isFailCalled());
        assertEquals("Invalid YouTube URL. Please enter a valid YouTube link.", presenter.getErrorMessage());
    }



    /**
     * Test presenter implementation for testing purposes.
     * Captures calls to success and fail methods.
     */
    private static class TestURLPresenter implements URLOutputBoundary {
        private boolean successCalled = false;
        private boolean failCalled = false;
        private URLOutputData outputData;
        private String errorMessage;

        @Override
        public void prepareSuccessView(URLOutputData outputData) {
            this.successCalled = true;
            this.outputData = outputData;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            this.failCalled = true;
            this.errorMessage = errorMessage;
        }

        public boolean isSuccessCalled() {
            return successCalled;
        }

        public boolean isFailCalled() {
            return failCalled;
        }

        public URLOutputData getOutputData() {
            return outputData;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
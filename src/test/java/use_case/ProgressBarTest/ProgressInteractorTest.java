package use_case.ProgressBarTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.progress.ProgressInteractor;
import use_case.progress.ProgressOutputBoundary;
import use_case.progress.ProgressOutputData;

import static org.junit.jupiter.api.Assertions.*;

class ProgressInteractorTest {

    private ProgressInteractor interactor;
    private FakePresenter presenter;

    @BeforeEach
    void setUp() {
        presenter = new FakePresenter();
        interactor = new ProgressInteractor(presenter);
    }

    /**
     * A fake presenter to capture output data for assertions.
     */
    private static class FakePresenter implements ProgressOutputBoundary {

        ProgressOutputData lastData;
        boolean updateCalled = false;
        boolean completeCalled = false;
        boolean errorCalled = false;

        @Override
        public void updateProgress(ProgressOutputData data) {
            updateCalled = true;
            lastData = data;
        }

        @Override
        public void complete(ProgressOutputData data) {
            completeCalled = true;
            lastData = data;
        }

        @Override
        public void error(ProgressOutputData data) {
            errorCalled = true;
            lastData = data;
        }
    }

    @Test
    void testReportProgressWhenNotCancelled() {
        interactor.reportProgress(30, "Downloading 30%");

        assertTrue(presenter.updateCalled);
        assertFalse(presenter.completeCalled);
        assertFalse(presenter.errorCalled);

        assertEquals(30, presenter.lastData.getPercent());
        assertEquals("Downloading 30%", presenter.lastData.getMessage());
        assertFalse(presenter.lastData.isDone());
        assertFalse(presenter.lastData.isCancelled());
    }

    @Test
    void testReportProgressWhenCancelledDoesNothing() {
        interactor.cancel();  // Mark cancelled
        presenter.updateCalled = false;

        interactor.reportProgress(80, "Should not show");

        // update should NOT be called
        assertFalse(presenter.updateCalled);
        // error should have happened due to cancel()
        assertTrue(presenter.errorCalled);

        // ensure lastData is from cancel(), not from reportProgress
        assertEquals("Download has been cancelled.", presenter.lastData.getMessage());
    }

    @Test
    void testCancelTriggersErrorPresenter() {
        interactor.cancel();

        assertTrue(presenter.errorCalled);
        assertEquals("Download has been cancelled.", presenter.lastData.getMessage());
        assertTrue(presenter.lastData.isCancelled());
    }

    @Test
    void testIsCancelled() {
        assertFalse(interactor.isCancelled());
        interactor.cancel();
        assertTrue(interactor.isCancelled());
    }

    @Test
    void testCompleteProgressPath() {
        // Manually call presenter through interactor
        interactor.reportProgress(100, "Done");

        assertTrue(presenter.updateCalled);
        assertEquals(100, presenter.lastData.getPercent());
    }
}

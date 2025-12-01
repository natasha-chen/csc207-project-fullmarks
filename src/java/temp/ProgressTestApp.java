package temp;

import interface_adapter.ProgressBar.ProgressController;
import interface_adapter.ProgressBar.ProgressPresenter;
import interface_adapter.ProgressBar.ProgressViewModel;
import use_case.progress.ProgressInteractor;
import view.ProgressView;

import javax.swing.*;
import java.awt.*;

public class ProgressTestApp {

    public static void main(String[] args) {

        ProgressViewModel viewModel = new ProgressViewModel();
        ProgressPresenter presenter = new ProgressPresenter(viewModel);
        ProgressInteractor interactor = new ProgressInteractor(presenter);

        ProgressController controller =
                new ProgressController(null, interactor);

        ProgressView progressView = new ProgressView(viewModel);
        progressView.setProgressController(controller);

        JFrame frame = new JFrame("Progress Bar Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());
        frame.add(progressView);
        frame.setVisible(true);

        // ProgressBar increasing 5%
        new Thread(() -> {
            for (int i = 0; i <= 100; i += 5) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ignored) {}

                interactor.reportProgress(i, "Downloading... " + i + "%");

                if (interactor.isCancelled()) {
                    break;
                }
            }

            if (!interactor.isCancelled()) {
                interactor.reportProgress(100, "Done!");
            }

        }).start();
    }
}

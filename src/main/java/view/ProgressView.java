package view;

import interface_adapter.progress.ProgressController;
import interface_adapter.progress.ProgressState;
import interface_adapter.progress.ProgressViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** view of the progress bar **/
public class ProgressView extends JPanel implements PropertyChangeListener {
    private final String viewName = "progress";
    private final ProgressViewModel progressViewModel;

    private final JProgressBar progressBar = new JProgressBar(0, 100);
    private final JLabel statusLabel = new JLabel("Ready");
    private final JButton cancelButton = new JButton("Cancel");

    private ProgressController progressController;

    public ProgressView(ProgressViewModel progressViewModel) {
        this.progressViewModel = progressViewModel;
            this.progressViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Download Progress");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        progressBar.setStringPainted(true);

        cancelButton.addActionListener(e -> {
            if (progressController != null) {
                progressController.cancelDownload();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(progressBar);
        this.add(statusLabel);
        this.add(cancelButton);
    }

    /** Updating progress bar */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ProgressState state = (ProgressState) evt.getNewValue();
        progressBar.setValue((int) state.getPercent());
        if (state.isError()) {
            statusLabel.setText("X " + state.getMessage());
        } else {
            statusLabel.setText(state.getMessage());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setProgressController(ProgressController controller) {
        this.progressController = controller;
    }
}
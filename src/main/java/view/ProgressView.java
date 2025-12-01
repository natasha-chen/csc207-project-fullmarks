package view;

import interface_adapter.ProgressBar.ProgressController;
import interface_adapter.ProgressBar.ProgressState;
import interface_adapter.ProgressBar.ProgressViewModel;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** view of the progress bar **/
public class ProgressView extends JPanel implements PropertyChangeListener {
    private final String viewName = "progress";
    private final ProgressViewModel progressViewModel;
    private final ViewManagerModel viewManagerModel;

    private final JProgressBar progressBar = new JProgressBar(0, 100);
    private final JLabel statusLabel = new JLabel("Ready");
    private final JButton cancelButton = new JButton("Cancel");
    private final JButton continueButton = new JButton("Continue");

    private ProgressController progressController;

    public ProgressView(ProgressViewModel progressViewModel,
                        ViewManagerModel viewManagerModel) {
        this.progressViewModel = progressViewModel;
        this.progressViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;

        final JLabel title = new JLabel("Download Progress");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        progressBar.setStringPainted(true);

        cancelButton.addActionListener(e -> {
            if (progressController != null) {
                progressController.cancelDownload();
            }
        });

        continueButton.setVisible(false);  // initially hidden
        continueButton.addActionListener(e -> {
            viewManagerModel.setActiveView("select for conversion");
            viewManagerModel.firePropertyChanged();
        });

        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(progressBar);
        this.add(statusLabel);
        this.add(cancelButton);
        this.add(continueButton);
    }

    /** Updating progress bar */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ProgressState state = (ProgressState) evt.getNewValue();
        SwingUtilities.invokeLater(() -> progressBar.setValue((int) state.getPercent()));

        if (state.isError()) {
            statusLabel.setText("X " + state.getMessage());
        } else {
            statusLabel.setText(state.getMessage());
        }

        // If completed (percent == 100), disable Cancel and show Close
        if (state.isComplete()) {
            cancelButton.setEnabled(false);
            continueButton.setVisible(true);
        }

        // If cancelled or error, disable Cancel and show Close
        if (state.isError() && state.getMessage().contains("cancel")) {
            cancelButton.setEnabled(false);
            continueButton.setVisible(true);   //
        }


        // If error or cancel, disable Cancel
        if (state.isError()) {
            cancelButton.setEnabled(false);
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setProgressController(ProgressController controller) {
        this.progressController = controller;
    }
}

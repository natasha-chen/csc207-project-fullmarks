package view;

import interface_adapter.ProgressBar.ProgressController;
import interface_adapter.download.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.url.URLState;
import interface_adapter.url.URLViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DownloadView extends JPanel implements PropertyChangeListener {

    private final DownloadController controller;
    private final DownloadViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    private final ProgressController progressController;


    private final JTextField outputFolderField = new JTextField(20);
    private final JLabel statusLabel = new JLabel("");

    private final JButton chooseFolderButton = new JButton("Choose Folder");
    private final JButton downloadButton = new JButton("Download");
    private final JButton backButton = new JButton("Back");
    private final JButton nextButton = new JButton("Next");

    public DownloadView(DownloadController controller,
                        DownloadViewModel viewModel,
                        ViewManagerModel viewManagerModel,
                        ProgressController progressController) {

        this.controller = controller;
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.progressController = progressController;

        viewModel.addPropertyChangeListener(this);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        JLabel title = new JLabel("Download Video");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22));

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        this.add(title, gbc);

        // Output folder
        gbc.gridx = 0; gbc.gridy++;
        this.add(new JLabel("Save To Folder:"), gbc);

        gbc.gridx = 1;
        this.add(outputFolderField, gbc);

        gbc.gridy++;
        this.add(chooseFolderButton, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        this.add(downloadButton, gbc);

        gbc.gridy++;
        this.add(backButton, gbc);

        gbc.gridy++;
        nextButton.setVisible(false);
        nextButton.setEnabled(false);
        this.add(nextButton, gbc);

        // Status
        gbc.gridy++;
        statusLabel.setForeground(Color.BLUE);
        this.add(statusLabel, gbc);

        // Button actions
        chooseFolderButton.addActionListener(e -> chooseFolder());
        downloadButton.addActionListener(e -> {
            viewManagerModel.setActiveView("progress");
            viewManagerModel.firePropertyChanged();

            progressController.startDownload(
                    viewModel.getState().getUrl(),
                    outputFolderField.getText()
            );
        });

        backButton.addActionListener(e -> {
            nextButton.setVisible(false);
            nextButton.setEnabled(false);
            viewManagerModel.setActiveView("url");
            viewManagerModel.firePropertyChanged();
        });

        nextButton.addActionListener(e -> {
            viewManagerModel.setActiveView("select for conversion");
            viewManagerModel.firePropertyChanged();
        });
    }

    private void chooseFolder() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            outputFolderField.setText(chooser.getSelectedFile().getAbsolutePath() + "/");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DownloadState state = viewModel.getState();

        outputFolderField.setText(state.getOutputFolder());
        statusLabel.setText(state.getStatusMessage());
        if (viewModel.getState().isSuccess()) {
            nextButton.setVisible(true);
            nextButton.setEnabled(true);
        }
        else {
            nextButton.setVisible(false);
            nextButton.setEnabled(false);
        }
    }
}
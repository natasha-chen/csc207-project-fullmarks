package view;

import data_access.PathManager;
import interface_adapter.ProgressBar.ProgressController;
import interface_adapter.download.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.select_for_conversion.SelectForConversionViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class DownloadView extends JPanel implements PropertyChangeListener {

    private final DownloadController controller;
    private final DownloadViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    private final ProgressController progressController;

    private final PlaceholderTextField outputFolderField =
            new PlaceholderTextField("Select folder or use default…");

    private final JLabel statusLabel = new JLabel("");

    private final JButton chooseFolderButton = new JButton("Choose Folder");
    private final JButton downloadButton = new JButton("Download as MP4");
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

        // ---------- UI LAYOUT ----------
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Download Video as MP4");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.add(title, gbc);

        // Output folder
        gbc.gridx = 0; gbc.gridy++;
        this.add(new JLabel("Save To Folder:"), gbc);

        gbc.gridy++;
        this.add(outputFolderField, gbc);

        // Default path
        outputFolderField.setText(PathManager.getDefaultDownloadFolder());

        // "Choose folder" button
        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        this.add(chooseFolderButton, gbc);

        // Download button
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        this.add(downloadButton, gbc);

        // Back button
        gbc.gridy = 4;
        this.add(backButton, gbc);

        // Next button (hidden until download succeeds)
        gbc.gridy = 5;
        nextButton.setVisible(false);
        nextButton.setEnabled(false);
        this.add(nextButton, gbc);

        // Status label
        gbc.gridy = 6;
        statusLabel.setForeground(Color.BLUE);
        this.add(statusLabel, gbc);

        // ---------- BUTTON ACTIONS ----------

        chooseFolderButton.addActionListener(e -> chooseFolder());

        downloadButton.addActionListener(e -> {
            selectForConversionViewModel.getState().setLatestFilePath(outputFolderField.getText());
            System.out.println(selectForConversionViewModel.getState().getLatestFilePath());
            controller.execute(viewModel.getState().getUrl(), outputFolderField.getText());
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
            outputFolderField.setText(chooser.getSelectedFile().getAbsolutePath() + File.separator);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DownloadState state = viewModel.getState();

        // Update UI
        outputFolderField.setText(state.getOutputFolder());
        statusLabel.setText(state.getStatusMessage());

        if (state.isSuccess()) {
            nextButton.setVisible(true);
            nextButton.setEnabled(true);
        }
        else {
            nextButton.setVisible(false);
            nextButton.setEnabled(false);
        }
    }

    public void setSelectForConversionViewModel(SelectForConversionViewModel selectForConversionViewModel) {
        this.selectForConversionViewModel = selectForConversionViewModel;
    }
}

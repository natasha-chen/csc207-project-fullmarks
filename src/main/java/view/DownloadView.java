package view;

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
    private SelectForConversionViewModel selectForConversionViewModel;

    private final JTextField outputFolderField = new JTextField(20);
    private final JLabel statusLabel = new JLabel("");

    private final JButton chooseFolderButton = new JButton("Choose Folder");
    private final JButton downloadButton = new JButton("Download as MP4");
    private final JButton backButton = new JButton("Back");
    private final JButton nextButton = new JButton("Next");

    public DownloadView(DownloadController controller,
                        DownloadViewModel viewModel,
                        ViewManagerModel viewManagerModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;

        viewModel.addPropertyChangeListener(this);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        JLabel title = new JLabel("Download Video as MP4");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22));

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        this.add(title, gbc);

        // Output folder
        gbc.gridx = 0; gbc.gridy++;
        this.add(new JLabel("Save To Folder:"), gbc);

        gbc.gridy++;
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

    public void setSelectForConversionViewModel(SelectForConversionViewModel selectForConversionViewModel) {
        this.selectForConversionViewModel = selectForConversionViewModel;
    }
}
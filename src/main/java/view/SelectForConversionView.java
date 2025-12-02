package view;

import interface_adapter.select_for_conversion.SelectForConversionController;
import interface_adapter.select_for_conversion.SelectForConversionState;
import interface_adapter.select_for_conversion.SelectForConversionViewModel;
import custom_datatype.VideoData;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import java.awt.event.ItemEvent;
import javax.swing.*;

/**
 * The View for the Playlist Use Case.
 */
public class SelectForConversionView extends JPanel implements PropertyChangeListener {
    private static final String VIEW_NAME = "Select for Conversion";


    private final JButton skip;
    private final JButton begin;
    private final JPanel bodyPanelLeft = new JPanel();
    private final JPanel bodyPanelRight = new JPanel();

    public SelectForConversionView(SelectForConversionController selectForConversionController,
                                   SelectForConversionViewModel selectForConversionViewModel) {
        selectForConversionViewModel.addPropertyChangeListener(this);
        SelectForConversionState currentState = selectForConversionViewModel.getState();

        final JLabel title = new JLabel(SelectForConversionViewModel.TITLE_LABEL);
        title.setAlignmentX(CENTER_ALIGNMENT);

        final JPanel bodyPanel = new JPanel();
        bodyPanelLeft.setLayout(new BoxLayout(bodyPanelLeft, BoxLayout.Y_AXIS));
        bodyPanelRight.setLayout(new BoxLayout(bodyPanelRight, BoxLayout.Y_AXIS));
        final JLabel bodyPanelLeftLabel = new JLabel(SelectForConversionViewModel.VIDEO_TITLE_LABEL);
        final JLabel bodyPanelRightLabel = new JLabel(SelectForConversionViewModel.MP3_LABEL);
        bodyPanelLeft.add(bodyPanelLeftLabel);
        bodyPanelRight.add(bodyPanelRightLabel);
        List<VideoData> playlist = currentState.getPlaylistData();
        if (playlist != null) {
            for (VideoData videoData : playlist) {
                bodyPanelLeft.add(new JLabel(videoData.getTitle()));
                JCheckBox mp3Check = new JCheckBox("");

                mp3Check.addItemListener(evt ->
                        videoData.setMP3Bool(evt.getStateChange() == ItemEvent.SELECTED));
                bodyPanelRight.add(mp3Check);
            }
        }
        bodyPanel.add(bodyPanelLeft);
        bodyPanel.add(bodyPanelRight);

        final JPanel buttonPanel = new JPanel();
        skip = new JButton(SelectForConversionViewModel.SKIP_BUTTON_LABEL);
        begin = new JButton(SelectForConversionViewModel.BEGIN_BUTTON_LABEL);
        buttonPanel.add(begin);
        buttonPanel.add(skip);

        skip.addActionListener(evt -> selectForConversionController.switchToUrlView());

        begin.addActionListener(evt -> selectForConversionController.execute(
                currentState.getPlaylistData(),
                currentState.getLatestFilePath()));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(bodyPanel);
        this.add(buttonPanel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SelectForConversionState state = (SelectForConversionState) evt.getNewValue();

        // Show error if present
        if (state.getSelectForConversionError() != null) {
            JOptionPane.showMessageDialog(this, state.getSelectForConversionError());
        }

        System.out.println("Current input folder:" + state.getLatestFilePath());

        // Update video list
        List<VideoData> playlist = state.getPlaylistData();
        if (playlist != null) {
            bodyPanelLeft.removeAll();
            bodyPanelRight.removeAll();

            // Add labels
            bodyPanelLeft.add(new JLabel(SelectForConversionViewModel.VIDEO_TITLE_LABEL));
            bodyPanelRight.add(new JLabel(SelectForConversionViewModel.MP3_LABEL));

            for (VideoData videoData : playlist) {
                bodyPanelLeft.add(new JLabel(videoData.getTitle()));

                JCheckBox mp3Check = new JCheckBox("");
                mp3Check.setSelected(videoData.isMP3Bool());
                mp3Check.addItemListener(evt2 ->
                        videoData.setMP3Bool(evt2.getStateChange() == ItemEvent.SELECTED));
                bodyPanelRight.add(mp3Check);
            }

            bodyPanelLeft.revalidate();
            bodyPanelLeft.repaint();
            bodyPanelRight.revalidate();
            bodyPanelRight.repaint();
        }
    }
}

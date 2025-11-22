package view;

import interface_adapter.select_for_conversion.SelectForConversionController;
import interface_adapter.select_for_conversion.SelectForConversionState;
import interface_adapter.select_for_conversion.SelectForConversionViewModel;
import custom_datatype.VideoData;

import java.util.List;

import java.awt.event.ItemEvent;
import javax.swing.*;

/**
 * The View for the Playlist Use Case.
 */
public class PlaylistView extends JPanel {
    private static final String VIEW_NAME = "Select for Conversion";

    private transient SelectForConversionViewModel selectForConversionViewModel;
    private transient SelectForConversionController selectForConversionController = null;
    private transient SelectForConversionState currentState;

    private final JButton skip;
    private final JButton begin;

    public PlaylistView(SelectForConversionViewModel selectForConversionViewModel) {
        this.currentState = selectForConversionViewModel.getState();

        this.selectForConversionViewModel = selectForConversionViewModel;

        final JLabel title = new JLabel(SelectForConversionViewModel.TITLE_LABEL);
        title.setAlignmentX(CENTER_ALIGNMENT);

        final JPanel bodyPanel = new JPanel();
        final JPanel bodyPanelLeft = new JPanel();
        final JPanel bodyPanelRight = new JPanel();
        bodyPanelLeft.setLayout(new BoxLayout(bodyPanelLeft, BoxLayout.Y_AXIS));
        bodyPanelRight.setLayout(new BoxLayout(bodyPanelRight, BoxLayout.Y_AXIS));
        final JLabel bodyPanelLeftLabel = new JLabel(SelectForConversionViewModel.VIDEO_TITLE_LABEL);
        final JLabel bodyPanelRightLabel = new JLabel(SelectForConversionViewModel.MP3_LABEL);
        bodyPanelLeft.add(bodyPanelLeftLabel);
        bodyPanelRight.add(bodyPanelRightLabel);
        List<VideoData> playlist = currentState.getPlaylistData();
        for (int i = 0; i < playlist.size();  i++) {
            final int index = i;
            bodyPanelLeft.add(new JLabel(playlist.get(index).getTitle()));
            JCheckBox mp3Check = new JCheckBox("");

            mp3Check.addItemListener(evt -> playlist.get(index).setMp3Bool(evt.getStateChange() == ItemEvent.SELECTED));
            bodyPanelRight.add(mp3Check);
        }
        bodyPanel.add(bodyPanelLeft);
        bodyPanel.add(bodyPanelRight);

        final JPanel buttonPanel = new JPanel();
        skip = new JButton(SelectForConversionViewModel.SKIP_BUTTON_LABEL);
        begin = new JButton(SelectForConversionViewModel.BEGIN_BUTTON_LABEL);
        buttonPanel.add(begin);
        buttonPanel.add(skip);

        skip.addActionListener(evt -> selectForConversionController.switchToUrlView());

        begin.addActionListener(evt -> selectForConversionController.execute(currentState.getPlaylistData()));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(bodyPanel);
        this.add(buttonPanel);
    }

    public String getViewName() {
        return VIEW_NAME;
    }

    public void setSelectForConversionController(SelectForConversionController controller) {
        this.selectForConversionController = controller;
    }
}

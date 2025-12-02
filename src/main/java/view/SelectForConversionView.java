package view;

import interface_adapter.select_for_conversion.SelectForConversionController;
import interface_adapter.select_for_conversion.SelectForConversionState;
import interface_adapter.select_for_conversion.SelectForConversionViewModel;
import custom_datatype.VideoData;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import java.awt.event.ItemEvent;
import java.awt.*;
import javax.swing.*;

public class SelectForConversionView extends JPanel implements PropertyChangeListener {
    private static final String VIEW_NAME = "Select for Conversion";

    private final JButton done;
    private final JButton begin;

    private final JPanel listPanel = new JPanel();

    // NEW: header checkboxes
    private final JCheckBox selectAll = new JCheckBox("Select All");
    private final JCheckBox clearAll = new JCheckBox("Clear All");

    public SelectForConversionView(SelectForConversionController controller,
                                   SelectForConversionViewModel viewModel) {

        viewModel.addPropertyChangeListener(this);
        SelectForConversionState currentState = viewModel.getState();

        // Title
        final JLabel title = new JLabel(SelectForConversionViewModel.TITLE_LABEL);
        title.setAlignmentX(CENTER_ALIGNMENT);

        // List panel config
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        buildVideoRows(currentState.getPlaylistData());

        // SCROLL SUPPORT
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setAlignmentX(CENTER_ALIGNMENT);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);

        // Buttons
        final JPanel buttonPanel = new JPanel();
        done = new JButton(SelectForConversionViewModel.DONE_BUTTON_LABEL);
        begin = new JButton(SelectForConversionViewModel.BEGIN_BUTTON_LABEL);
        buttonPanel.add(begin);
        buttonPanel.add(done);

        done.addActionListener(evt -> controller.switchToMenuView());

        begin.addActionListener(evt -> {
            controller.execute(
                    currentState.getPlaylistData(),
                    currentState.getLatestFilePath(),
                    currentState.getUsername()
            );
        });

        // VIEW LAYOUT
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(createHeaderPanel()); // NEW HEADER
        this.add(scrollPane);
        this.add(buttonPanel);

        addHeaderListeners(currentState.getPlaylistData()); // NEW
    }

    /** Header panel containing MP3 label + Select All + Clear All */
    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.add(new JLabel("MP3"));
        header.add(selectAll);
        header.add(clearAll);
        return header;
    }

    /** Adds listeners to Select All and Clear All checkboxes */
    private void addHeaderListeners(List<VideoData> playlist) {
        selectAll.addActionListener(evt -> {
            if (playlist != null) {
                for (VideoData video : playlist) {
                    video.setMP3Bool(true);
                }
                updateCheckboxes(true);
            }
        });

        clearAll.addActionListener(evt -> {
            if (playlist != null) {
                for (VideoData video : playlist) {
                    video.setMP3Bool(false);
                }
                updateCheckboxes(false);
            }
        });
    }

    /** Rebuild checkboxes visually to reflect header changes */
    private void updateCheckboxes(boolean value) {
        for (Component c : listPanel.getComponents()) {
            JPanel row = (JPanel) c;
            JCheckBox box = (JCheckBox) row.getComponent(0);
            box.setSelected(value);
        }
    }

    /** Build the list of label+checkbox rows */
    private void buildVideoRows(List<VideoData> playlist) {
        listPanel.removeAll();

        if (playlist != null) {
            for (VideoData videoData : playlist) {

                JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));

                JCheckBox mp3Check = new JCheckBox();
                mp3Check.setSelected(videoData.isMP3Bool());

                mp3Check.addItemListener(evt ->
                        videoData.setMP3Bool(evt.getStateChange() == ItemEvent.SELECTED));

                JLabel titleLabel = new JLabel(videoData.getTitle());

                row.add(mp3Check);
                row.add(titleLabel);

                listPanel.add(row);
            }
        }

        listPanel.revalidate();
        listPanel.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SelectForConversionState state = (SelectForConversionState) evt.getNewValue();

        if (state.getSelectForConversionError() != null) {
            JOptionPane.showMessageDialog(this, state.getSelectForConversionError());
        }

        // Rebuild rows after state update
        buildVideoRows(state.getPlaylistData());

        // Reconnect header actions after update
        addHeaderListeners(state.getPlaylistData());
    }

    public String getViewName() {
        return VIEW_NAME;
    }
}

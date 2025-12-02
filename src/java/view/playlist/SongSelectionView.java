package view.playlist;

import interface_adapter.song_selection.SongSelectionController;
import interface_adapter.song_selection.SongSelectionViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * Dialog for selecting songs to add to a playlist.
 * For now, it shows whatever the SongSelectionViewModel exposes as availableSongs.
 */
public class SongSelectionView extends JDialog implements PropertyChangeListener {

    private final String playlistName;
    private final SongSelectionController controller;
    private final SongSelectionViewModel viewModel;

    private final JList<String> songList = new JList<>();
    private final JButton addButton = new JButton("Add");
    private final JButton cancelButton = new JButton("Cancel");

    public SongSelectionView(String playlistName,
                             SongSelectionController controller,
                             SongSelectionViewModel viewModel) {
        this.playlistName = playlistName;
        this.controller = controller;
        this.viewModel = viewModel;

        this.viewModel.addPropertyChangeListener(this);

        setTitle("Add songs to: " + playlistName);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        initLayout();
        wireListeners();

        // Initialize list from view model
        updateSongList();
    }

    private void initLayout() {
        setLayout(new BorderLayout(10, 10));

        songList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        add(new JScrollPane(songList), BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.add(addButton);
        bottom.add(cancelButton);
        add(bottom, BorderLayout.SOUTH);
    }

    private void wireListeners() {
        addButton.addActionListener(e -> {
            List<String> selected = songList.getSelectedValuesList();
            if (selected.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please select at least one song.",
                        "No selection",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            controller.addSongs(playlistName, selected);
            // Interactor/presenter will call viewModel.requestClose() on success.
        });

        cancelButton.addActionListener(e -> dispose());
    }

    private void updateSongList() {
        java.util.List<String> songs = viewModel.getAvailableSongs();
        songList.setListData(songs.toArray(new String[0]));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case SongSelectionViewModel.AVAILABLE_SONGS -> updateSongList();
            case SongSelectionViewModel.CLOSE -> {
                if (Boolean.TRUE.equals(evt.getNewValue())) {
                    dispose();
                }
            }
        }
    }
}

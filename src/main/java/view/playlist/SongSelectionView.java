package main.java.view.playlist;

import main.java.interface_adapter.song_selection.SongSelectionController;
import main.java.interface_adapter.song_selection.SongSelectionViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SongSelectionView extends JDialog implements PropertyChangeListener {

    private final String playlistName;
    private final SongSelectionController controller;
    private final SongSelectionViewModel viewModel;

    private final JList<String> songList = new JList<>();
    private final JButton confirm = new JButton("Confirm");
    private final JButton cancel = new JButton("Cancel");

    public SongSelectionView(String playlistName,
                             SongSelectionController controller,
                             SongSelectionViewModel vm) {

        this.playlistName = playlistName;
        this.controller = controller;
        this.viewModel = vm;

        vm.addPropertyChangeListener(this);

        setModal(true);
        setLayout(new BorderLayout());

        add(new JScrollPane(songList), BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.add(confirm);
        bottom.add(cancel);
        add(bottom, BorderLayout.SOUTH);

        confirm.addActionListener(e -> {
            var selected = songList.getSelectedValuesList();
            controller.addSongs(playlistName, selected);
        });

        cancel.addActionListener(e -> dispose());

        setSize(350, 400);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case SongSelectionViewModel.AVAILABLE_SONGS -> {
                songList.setListData(viewModel.getAvailableSongs().toArray(new String[0]));
            }
            case SongSelectionViewModel.CLOSE -> dispose();
        }
    }
}

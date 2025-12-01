package view.playlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.modify_playlist.ModifyPlaylistController;
import interface_adapter.playlist_view.PlaylistViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class PlaylistView extends JPanel implements PropertyChangeListener {

    public static final String VIEW_NAME = "playlist";

    private final PlaylistViewModel playlistViewModel;
    private final ModifyPlaylistController modifyPlaylistController;
    private final ViewManagerModel viewManagerModel;

    private final JLabel playlistNameLabel = new JLabel();
    private final JList<String> songList = new JList<>();
    private final JLabel errorLabel = new JLabel();

    private final JButton addSongButton = new JButton("Add Song(s)");
    private final JButton deleteButton = new JButton("Delete");
    private final JButton playButton = new JButton("Play");
    private final JButton libraryButton = new JButton("Library");

    public PlaylistView(PlaylistViewModel playlistViewModel,
                        ModifyPlaylistController modifyPlaylistController,
                        ViewManagerModel viewManagerModel) {

        this.playlistViewModel = playlistViewModel;
        this.modifyPlaylistController = modifyPlaylistController;
        this.viewManagerModel = viewManagerModel;

        this.playlistViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout(10, 10));

        // top: playlist name + error
        JPanel top = new JPanel(new BorderLayout());
        top.add(playlistNameLabel, BorderLayout.WEST);
        top.add(errorLabel, BorderLayout.SOUTH);
        add(top, BorderLayout.NORTH);

        // center: song list
        songList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(songList), BorderLayout.CENTER);

        // bottom: buttons
        JPanel bottom = new JPanel();
        bottom.add(addSongButton);
        bottom.add(deleteButton);
        bottom.add(playButton);
        bottom.add(libraryButton);
        add(bottom, BorderLayout.SOUTH);

        wireListeners();
    }

    private void wireListeners() {
        // Add Song(s) – simplified: type a song ID/name
        addSongButton.addActionListener(e -> {
            String playlistName = playlistViewModel.getPlaylistName();
            String songId = JOptionPane.showInputDialog(
                    this,
                    "Enter song ID / filename to add:",
                    "Add song to " + playlistName,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (songId == null || songId.isBlank()) {
                return; // cancelled or empty
            }

            modifyPlaylistController.addSong(playlistName, songId.trim());
        });

        // Delete song → confirm → ModifyPlaylistController
        deleteButton.addActionListener(e -> {
            int idx = songList.getSelectedIndex();
            if (idx == -1) {
                playlistViewModel.setErrorMessage("please select song");
                return;
            }

            String playlistName = playlistViewModel.getPlaylistName();
            String songId = songList.getSelectedValue(); // each entry is a unique ID/name

            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete?\nThis can't be undone",
                    "Confirm delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                modifyPlaylistController.removeSong(playlistName, songId);
            }
        });

        // Play button – stub for now
        playButton.addActionListener(e ->
                playlistViewModel.setErrorMessage("Play playlist is not implemented yet.")
        );

        // Back to library
        libraryButton.addActionListener(e -> {
            viewManagerModel.setActiveView("library");
            viewManagerModel.firePropertyChanged();
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case PlaylistViewModel.PLAYLIST_NAME_PROPERTY -> {
                playlistNameLabel.setText(playlistViewModel.getPlaylistName());
            }
            case PlaylistViewModel.SONG_NAMES_PROPERTY -> {
                List<String> songs = playlistViewModel.getSongNames();
                songList.setListData(songs.toArray(new String[0]));
            }
            case PlaylistViewModel.ERROR_MESSAGE_PROPERTY -> {
                errorLabel.setText(playlistViewModel.getErrorMessage());
            }
        }
    }

    public String getViewName() {
        return VIEW_NAME;
    }
}

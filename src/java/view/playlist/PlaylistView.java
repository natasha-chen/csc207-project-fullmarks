package view.playlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.playlist_view.PlaylistViewModel;
import interface_adapter.playlist_view.PlaylistController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class PlaylistView extends JPanel implements PropertyChangeListener {

    public static final String VIEW_NAME = "playlist";

    private final PlaylistViewModel viewModel;
    private final PlaylistController playlistController;
    private final ViewManagerModel viewManagerModel;

    private final JLabel playlistNameLabel = new JLabel();
    private final JList<String> songList = new JList<>();
    private final JLabel errorLabel = new JLabel();

    private final JButton addSongButton = new JButton("Add Song(s)");
    private final JButton deleteButton = new JButton("Delete");
    private final JButton playButton = new JButton("Play");
    private final JButton libraryButton = new JButton("Library");

    public PlaylistView(PlaylistViewModel viewModel,
                        PlaylistController playlistController,
                        ViewManagerModel viewManagerModel) {

        this.viewModel = viewModel;
        this.playlistController = playlistController;
        this.viewManagerModel = viewManagerModel;

        this.viewModel.addPropertyChangeListener(this);

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

        // listeners
        wireListeners();
    }

    private void wireListeners() {

        // Add Song(s) → dialog (uses ModifyPlaylist for ADD)
        addSongButton.addActionListener(e -> {
            String playlistName = viewModel.getPlaylistName();
            new SongSelectionView(
                    playlistName,
                    modifyPlaylistController,
                    viewModel
            ).setVisible(true);
        });

        // Delete song → confirm (uses ModifyPlaylist for REMOVE)
        deleteButton.addActionListener(e -> {
            int idx = songList.getSelectedIndex();
            if (idx == -1) {
                viewModel.setErrorMessage("please select song");
                return;
            }

            String playlistName = viewModel.getPlaylistName();
            String songId = viewModel.getSongIdAt(idx);   // from VM → underlying entity

            Runnable onConfirm = () ->
                    modifyPlaylistController.removeSong(playlistName, songId);

            new ConfirmationView(
                    "Are you sure you want to delete?\nThis can't be undone",
                    onConfirm
            ).setVisible(true);
        });

        // Play whole playlist OR start from highlighted song
        playButton.addActionListener(e -> {
            String playlistName = viewModel.getPlaylistName();
            int idx = songList.getSelectedIndex();

            if (idx == -1) {
                playPlaylistController.play(playlistName);             // whole playlist
            } else {
                playPlaylistController.playFromIndex(playlistName, idx);  // optional; depends on your friend
            }
        });

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
                playlistNameLabel.setText(viewModel.getPlaylistName());
            }
            case PlaylistViewModel.SONG_NAMES_PROPERTY -> {
                List<String> songs = viewModel.getSongNames();
                songList.setListData(songs.toArray(new String[0]));
            }
            case PlaylistViewModel.ERROR_MESSAGE_PROPERTY -> {
                errorLabel.setText(viewModel.getErrorMessage());
            }
        }
    }

    public String getViewName() {
        return VIEW_NAME;
    }
}

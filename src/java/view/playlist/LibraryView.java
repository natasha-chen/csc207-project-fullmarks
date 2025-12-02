package view.playlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_playlist.CreatePlaylistController;
import interface_adapter.library.LibraryController;
import interface_adapter.library.LibraryState;
import interface_adapter.library.LibraryViewModel;
import interface_adapter.playlist_view.PlaylistViewModel;
import interface_adapter.delete_playlist.DeletePlaylistController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class LibraryView extends JPanel implements PropertyChangeListener {

    public static final String VIEW_NAME = "library";

    private final LibraryViewModel viewModel;
    private final LibraryController controller;
    private final DeletePlaylistController deletePlaylistController;
    private final ViewManagerModel viewManagerModel;
    private final PlaylistViewModel playlistViewModel;
    private final CreatePlaylistController createPlaylistController;

    private final JList<String> playlistList = new JList<>();
    private final JButton menuButton = new JButton("Menu");
    private final JButton newPlaylistButton = new JButton("New Playlist");
    private final JButton deleteButton = new JButton("Delete Playlist");
    private final JButton playButton = new JButton("Play");
    private final JButton enterButton = new JButton("Enter Playlist");
    private final JLabel errorLabel = new JLabel();

    public LibraryView(LibraryViewModel viewModel,
                       LibraryController controller,
                       DeletePlaylistController deletePlaylistController,
                       CreatePlaylistController createPlaylistController,
                       PlaylistViewModel playlistViewModel,
                       ViewManagerModel viewManagerModel) {

        this.viewModel = viewModel;
        this.controller = controller;
        this.deletePlaylistController = deletePlaylistController;
        this.createPlaylistController = createPlaylistController;
        this.viewManagerModel = viewManagerModel;
        this.playlistViewModel = playlistViewModel;
        this.viewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        // Top: title + menu
        JPanel top = new JPanel(new BorderLayout());
        top.add(new JLabel("Library"), BorderLayout.WEST);
        top.add(menuButton, BorderLayout.EAST);
        add(top, BorderLayout.NORTH);

        // Center: playlist list
        playlistList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(playlistList), BorderLayout.CENTER);

        // Bottom: buttons + error label
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newPlaylistButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(playButton);
        buttonPanel.add(enterButton);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(buttonPanel, BorderLayout.CENTER);
        bottom.add(errorLabel, BorderLayout.SOUTH);

        add(bottom, BorderLayout.SOUTH);

        wireListeners();

        // initial load
        controller.loadLibrary();
    }

    private void wireListeners() {
        // double-click on playlist – later wire to LoadPlaylist + switch view
        playlistList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !playlistList.isSelectionEmpty()) {
                    int index = playlistList.getSelectedIndex();
                    String playlistName = playlistList.getModel().getElementAt(index);
                    // TODO: call a PlaylistController here when you have it
                    System.out.println("Double-clicked playlist: " + playlistName);
                }
            }
        });

        menuButton.addActionListener(e -> {
            viewManagerModel.setActiveView("menu");
            viewManagerModel.firePropertyChanged();
        });

        // NEW: create playlist popup using CreatePlaylistController
        newPlaylistButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(
                    this,
                    "Enter playlist name:",
                    "Create Playlist",
                    JOptionPane.PLAIN_MESSAGE
            );
            if (name == null || name.isBlank()) {
                return; // user cancelled or empty
            }
            createPlaylistController.createPlaylist(name.trim());
        });

        // delete button listener
        deleteButton.addActionListener(e -> {
            if (playlistList.isSelectionEmpty()) {
                errorLabel.setText("Please select a playlist to delete.");
                return;
            }

            int index = playlistList.getSelectedIndex();
            String playlistName = playlistList.getModel().getElementAt(index);

            // Optional: Swing confirmation dialog
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete playlist \"" + playlistName + "\"?",
                    "Delete Playlist",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            // Call the Delete Playlist use case
            deletePlaylistController.execute(playlistName);

            // Clear UI error state
            errorLabel.setText("");

            // Reload library (you already have a libraryController)
            controller.execute();

            System.out.println("Deleted playlist: " + playlistName);
        });


        playButton.addActionListener(e -> {
            if (playlistList.isSelectionEmpty()) {
                errorLabel.setText("Please select a playlist to play.");
            } else {
                int index = playlistList.getSelectedIndex();
                String playlistName = playlistList.getModel().getElementAt(index);
                // TODO: call PlayPlaylist use case (friend's part)
                System.out.println("Play playlist: " + playlistName);
            }
        });

        enterButton.addActionListener(e -> {
            if (playlistList.isSelectionEmpty()) {
                errorLabel.setText("Please select a playlist to enter.");
                return;
            }

            String playlistName = playlistList.getSelectedValue();

            playlistViewModel.setPlaylistName(playlistName);
            playlistViewModel.firePropertyChanged();

            viewManagerModel.setActiveView("playlist_view");
            viewManagerModel.firePropertyChanged();
        });

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            LibraryState state = viewModel.getState();
            List<String> names = state.getPlaylistNames();
            playlistList.setListData(names.toArray(new String[0]));
            errorLabel.setText(state.getErrorMessage());
        }
    }

    public String getViewName() {
        return VIEW_NAME;
    }
}

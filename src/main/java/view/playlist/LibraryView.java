package main.java.view.playlist;   // adjust to your real package

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.library.LibraryController;
import main.java.interface_adapter.library.LibraryState;
import main.java.interface_adapter.library.LibraryViewModel;

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
    private final ViewManagerModel viewManagerModel;

    private final JList<String> playlistList = new JList<>();
    private final JButton menuButton = new JButton("Menu");
    private final JButton newPlaylistButton = new JButton("New Playlist");
    private final JButton deleteButton = new JButton("Delete");
    private final JButton playButton = new JButton("Play");
    private final JLabel errorLabel = new JLabel();

    public LibraryView(LibraryViewModel viewModel,
                       LibraryController controller,
                       ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.viewManagerModel = viewManagerModel;

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

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(buttonPanel, BorderLayout.CENTER);
        bottom.add(errorLabel, BorderLayout.SOUTH);

        add(bottom, BorderLayout.SOUTH);

        wireListeners();

        // initial load
        controller.loadLibrary();
    }

    private void wireListeners() {
        // double-click on playlist – TODO: later wire to LoadPlaylist + switch view
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

        newPlaylistButton.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "TODO: open NewPlaylist dialog", "Not implemented",
                        JOptionPane.INFORMATION_MESSAGE));

        deleteButton.addActionListener(e -> {
            if (playlistList.isSelectionEmpty()) {
                errorLabel.setText("Please select a playlist to delete.");
            } else {
                int index = playlistList.getSelectedIndex();
                String playlistName = playlistList.getModel().getElementAt(index);
                // TODO: show confirmation + call DeletePlaylist use case
                System.out.println("Delete playlist: " + playlistName);
            }
        });

        playButton.addActionListener(e -> {
            if (playlistList.isSelectionEmpty()) {
                errorLabel.setText("Please select a playlist to play.");
            } else {
                int index = playlistList.getSelectedIndex();
                String playlistName = playlistList.getModel().getElementAt(index);
                // TODO: call PlayPlaylist use case (your friend's part)
                System.out.println("Play playlist: " + playlistName);
            }
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

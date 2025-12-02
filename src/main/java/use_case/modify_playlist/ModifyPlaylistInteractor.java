package use_case.modify_playlist;

import data_access.PlaylistDataAccessInterface;
import entity.Playlist;

import java.util.List;

/**
 * Interactor for adding/removing songs from a playlist.
 */
public class ModifyPlaylistInteractor implements ModifyPlaylistInputBoundary {

    private final PlaylistDataAccessInterface playlistGateway;
    private final ModifyPlaylistOutputBoundary presenter;

    public ModifyPlaylistInteractor(PlaylistDataAccessInterface playlistGateway,
                                    ModifyPlaylistOutputBoundary presenter) {
        this.playlistGateway = playlistGateway;
        this.presenter = presenter;
    }

    @Override
    public void execute(ModifyPlaylistInputData inputData) {
        String playlistName = inputData.getPlaylistName();
        String songId = inputData.getSongId();
        String operation = inputData.getOperation();

        Playlist playlist = playlistGateway.getPlaylist(playlistName);
        if (playlist == null) {
            presenter.prepareFailView("Playlist not found: " + playlistName);
            return;
        }

        switch (operation) {
            case "ADD" -> playlist.addSong(songId);
            case "REMOVE" -> playlist.removeSong(songId);
            default -> {
                presenter.prepareFailView("Unknown operation: " + operation);
                return;
            }
        }

        // Persist changes
        playlistGateway.savePlaylist(playlist);

        // Build output
        List<String> updatedSongIds = playlist.getAllSongId();
        List<String> updatedSongNames = playlist.getAllSongNames();
        ModifyPlaylistOutputData outputData =
                new ModifyPlaylistOutputData(playlistName, updatedSongIds, updatedSongNames);

        presenter.prepareSuccessView(outputData);
    }
}

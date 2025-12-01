package main.java.interface_adapter.playlist_view;

import main.java.use_case.load_playlist.LoadPlaylistOutputBoundary;
import main.java.use_case.load_playlist.LoadPlaylistOutputData;
import main.java.use_case.modify_playlist.ModifyPlaylistOutputBoundary;
import main.java.use_case.modify_playlist.ModifyPlaylistOutputData;

/**
 * Presenter for the Playlist screen.
 * Adapts LoadPlaylist + ModifyPlaylist output into PlaylistViewModel.
 */
public class PlaylistPresenter implements
        LoadPlaylistOutputBoundary,
        ModifyPlaylistOutputBoundary {

    private final PlaylistViewModel viewModel;

    public PlaylistPresenter(PlaylistViewModel viewModel) {
        this.viewModel = viewModel;
    }

    // ------- LoadPlaylist -------

    @Override
    public void prepareSuccessView(LoadPlaylistOutputData outputData) {
        viewModel.setErrorMessage("");
        viewModel.setPlaylistName(outputData.getPlaylistName());
        viewModel.setSongNames(outputData.getSongNames());
    }

    @Override
    public void prepareFailView(String errorMessage) {
        viewModel.setPlaylistName("");
        viewModel.setSongNames(java.util.List.of());
        viewModel.setErrorMessage(errorMessage);
    }

    // ------- ModifyPlaylist (ADD / REMOVE song) -------

    @Override
    public void prepareSuccessView(ModifyPlaylistOutputData outputData) {
        // After modification, we usually want to show updated song list.
        viewModel.setErrorMessage("");
        viewModel.setSongNames(outputData.getUpdatedSongNames());
    }

    @Override
    public void prepareFailViewModify(String errorMessage) {
        // You can name this method differently depending on your interface;
        // the important point is: on failure we only update the error.
        viewModel.setErrorMessage(errorMessage);
    }
}

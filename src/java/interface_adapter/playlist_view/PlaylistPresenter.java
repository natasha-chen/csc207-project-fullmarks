package interface_adapter.playlist_view;

import interface_adapter.ViewManagerModel;
import use_case.playlist.PlaylistOutputBoundary;
import use_case.playlist.PlaylistOutputData;
import use_case.modify_playlist.ModifyPlaylistOutputBoundary;
import use_case.modify_playlist.ModifyPlaylistOutputData;

/**
 * Presenter for the Playlist screen.
 * Adapts LoadPlaylist + ModifyPlaylist output into PlaylistViewModel.
 */
public class PlaylistPresenter implements
        PlaylistOutputBoundary,
        ModifyPlaylistOutputBoundary {

    private final PlaylistViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public PlaylistPresenter(PlaylistViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    // ------- LoadPlaylist -------

    @Override
    public void prepareSuccessView(PlaylistOutputData outputData) {
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

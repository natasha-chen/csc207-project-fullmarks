package interface_adapter.modify_playlist;

import interface_adapter.playlist_view.PlaylistViewModel;
import use_case.modify_playlist.*;

public class ModifyPlaylistPresenter implements ModifyPlaylistOutputBoundary {

    private final PlaylistViewModel playlistViewModel;

    public ModifyPlaylistPresenter(PlaylistViewModel playlistViewModel) {
        this.playlistViewModel = playlistViewModel;
    }

    @Override
    public void prepareSuccessView(ModifyPlaylistOutputData data) {
        playlistViewModel.setPlaylistName(data.getPlaylistName());
        playlistViewModel.setSongNames(data.getSongIds());
        playlistViewModel.setErrorMessage(null);   // clear errors
        playlistViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        playlistViewModel.setErrorMessage(errorMessage);
        playlistViewModel.firePropertyChanged();
    }
}

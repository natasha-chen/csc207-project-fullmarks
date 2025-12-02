package interface_adapter.song_selection;

import use_case.modify_playlist.ModifyPlaylistOutputBoundary;
import use_case.modify_playlist.ModifyPlaylistOutputData;

public class SongSelectionPresenter implements ModifyPlaylistOutputBoundary {

    private final SongSelectionViewModel viewModel;

    public SongSelectionPresenter(SongSelectionViewModel vm) {
        this.viewModel = vm;
    }

    @Override
    public void prepareSuccessView(ModifyPlaylistOutputData data) {
        viewModel.requestClose();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // no error UI needed for now — dialog simply stays open
    }
}

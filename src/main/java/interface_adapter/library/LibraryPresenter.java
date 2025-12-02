package interface_adapter.library;

import use_case.playlist_library.PlaylistLibraryOutputBoundary;
import use_case.playlist_library.PlaylistLibraryOutputData;

public class LibraryPresenter implements PlaylistLibraryOutputBoundary {

    private final LibraryViewModel viewModel;

    public LibraryPresenter(LibraryViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(PlaylistLibraryOutputData outputData) {
        viewModel.setPlaylistNames(outputData.getPlaylistNames());
        viewModel.setErrorMessage("");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        viewModel.setPlaylistNames(java.util.List.of());
        viewModel.setErrorMessage(errorMessage);
    }
}

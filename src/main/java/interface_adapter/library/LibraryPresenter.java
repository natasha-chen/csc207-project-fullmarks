package main.java.interface_adapter.library;

import main.java.use_case.load_playlist_library.LoadPlaylistLibraryOutputBoundary;
import main.java.use_case.load_playlist_library.LoadPlaylistLibraryOutputData;

public class LibraryPresenter implements LoadPlaylistLibraryOutputBoundary {

    private final LibraryViewModel viewModel;

    public LibraryPresenter(LibraryViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(LoadPlaylistLibraryOutputData outputData) {
        viewModel.setPlaylistNames(outputData.getPlaylistNames());
        viewModel.setErrorMessage("");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        viewModel.setPlaylistNames(java.util.List.of());
        viewModel.setErrorMessage(errorMessage);
    }
}

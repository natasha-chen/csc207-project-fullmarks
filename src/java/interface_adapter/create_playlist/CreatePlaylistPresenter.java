package interface_adapter.create_playlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.library.LibraryViewModel;
import use_case.create_playlist.CreatePlaylistOutputBoundary;
import use_case.create_playlist.CreatePlaylistOutputData;

public class CreatePlaylistPresenter implements CreatePlaylistOutputBoundary {

    private final LibraryViewModel libraryViewModel;
    private final ViewManagerModel viewManagerModel;

    public CreatePlaylistPresenter(LibraryViewModel libraryViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.libraryViewModel = libraryViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CreatePlaylistOutputData data) {
        libraryViewModel.setPlaylistNames(data.getAllPlaylistNames());
        libraryViewModel.setErrorMessage("");

        // go to library to show the new playlist
        viewManagerModel.setActiveView("library");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        libraryViewModel.setErrorMessage(errorMessage);
        // stay on whatever view you’re on; no forced navigation
    }
}

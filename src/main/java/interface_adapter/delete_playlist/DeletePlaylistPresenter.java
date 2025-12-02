package interface_adapter.delete_playlist;

import interface_adapter.library.LibraryViewModel;
import use_case.delete_playlist.DeletePlaylistOutputBoundary;
import use_case.delete_playlist.DeletePlaylistOutputData;

public class DeletePlaylistPresenter implements DeletePlaylistOutputBoundary {

    private final LibraryViewModel libraryViewModel;

    public DeletePlaylistPresenter(LibraryViewModel libraryViewModel) {
        this.libraryViewModel = libraryViewModel;
    }

    @Override
    public void prepareSuccessView(DeletePlaylistOutputData data) {
        libraryViewModel.setPlaylistNames(data.getPlaylistNames());
        libraryViewModel.setErrorMessage("");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        libraryViewModel.setErrorMessage(errorMessage);
    }
}

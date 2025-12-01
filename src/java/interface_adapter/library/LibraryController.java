package interface_adapter.library;

import use_case.playlist_library.PlaylistLibraryInputBoundary;

public class LibraryController {

    private final PlaylistLibraryInputBoundary loadPlaylistLibraryInteractor;

    public LibraryController(PlaylistLibraryInputBoundary loadPlaylistLibraryInteractor) {
        this.loadPlaylistLibraryInteractor = loadPlaylistLibraryInteractor;
    }

    public void loadLibrary() {
        loadPlaylistLibraryInteractor.execute();
    }
}

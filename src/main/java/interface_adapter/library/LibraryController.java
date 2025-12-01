package main.java.interface_adapter.library;

import main.java.use_case.load_playlist_library.LoadPlaylistLibraryInputBoundary;

public class LibraryController {

    private final LoadPlaylistLibraryInputBoundary loadPlaylistLibraryInteractor;

    public LibraryController(LoadPlaylistLibraryInputBoundary loadPlaylistLibraryInteractor) {
        this.loadPlaylistLibraryInteractor = loadPlaylistLibraryInteractor;
    }

    public void loadLibrary() {
        loadPlaylistLibraryInteractor.execute();
    }
}

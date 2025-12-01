package main.java.use_case.load_playlist_library;

import java.util.List;

public class LoadPlaylistLibraryInteractor
        implements LoadPlaylistLibraryInputBoundary {

    private final PlaylistLibraryDsGateway gateway;
    private final LoadPlaylistLibraryOutputBoundary presenter;

    public LoadPlaylistLibraryInteractor(PlaylistLibraryDsGateway gateway,
                                         LoadPlaylistLibraryOutputBoundary presenter) {
        this.gateway = gateway;
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        // however you identify the current user – adjust as needed
        List<String> playlistNames = gateway.getPlaylistNamesForCurrentUser();

        if (playlistNames.isEmpty()) {
            presenter.prepareFailView("You have no playlists yet.");
        } else {
            LoadPlaylistLibraryOutputData out =
                    new LoadPlaylistLibraryOutputData(playlistNames);
            presenter.prepareSuccessView(out);
        }
    }
}

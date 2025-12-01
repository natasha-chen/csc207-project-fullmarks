package use_case.playlist_library;

import data_access.PlaylistDataAccessInterface;
import entity.Playlist;

import java.util.List;

public class PlaylistLibraryInteractor
        implements PlaylistLibraryInputBoundary {

    private final PlaylistDataAccessInterface DAI;
    private final PlaylistLibraryOutputBoundary presenter;

    public PlaylistLibraryInteractor(PlaylistDataAccessInterface DAI,
                                     PlaylistLibraryOutputBoundary presenter) {
        this.DAI = DAI;
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        // however you identify the current user – adjust as needed
        List<Playlist> playlistNames = DAI.getAllPlaylists();

        if (playlistNames.isEmpty()) {
            presenter.prepareFailView("You have no playlists yet.");
        } else {
            PlaylistLibraryOutputData out =
                    new PlaylistLibraryOutputData(playlistNames);
            presenter.prepareSuccessView(out);
        }
    }
}

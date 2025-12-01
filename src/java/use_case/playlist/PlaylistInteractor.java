package use_case.playlist;

import entity.Playlist;
import data_access.PlaylistDataAccessInterface;

public class PlaylistInteractor implements PlaylistInputBoundary {

    private final PlaylistDataAccessInterface playlistDAO;
    private final PlaylistOutputBoundary presenter;

    public PlaylistInteractor(PlaylistDataAccessInterface playlistDAO,
                              PlaylistOutputBoundary presenter) {
        this.playlistDAO = playlistDAO;
        this.presenter   = presenter;
    }

    @Override
    public void execute(PlaylistInputData inputData) {
        Playlist p = playlistDAO.getPlaylist(inputData.getPlaylistName());

        PlaylistOutputData output =
                new PlaylistOutputData(p.getName(), p.getSongs());

        presenter.prepareSuccessView(output);
    }
}

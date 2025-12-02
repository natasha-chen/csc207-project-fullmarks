package use_case.delete_playlist;

import data_access.PlaylistDataAccessInterface;
import entity.Playlist;

import java.util.List;
import java.util.stream.Collectors;

public class DeletePlaylistInteractor implements DeletePlaylistInputBoundary {

    private final PlaylistDataAccessInterface playlistDAO;
    private final DeletePlaylistOutputBoundary presenter;

    public DeletePlaylistInteractor(PlaylistDataAccessInterface playlistDAO,
                                    DeletePlaylistOutputBoundary presenter) {
        this.playlistDAO = playlistDAO;
        this.presenter = presenter;
    }

    @Override
    public void execute(DeletePlaylistInputData data) {
        String name = data.getPlaylistName();

        Playlist playlist = playlistDAO.getPlaylist(name);
        if (playlist == null) {
            presenter.prepareFailView("Playlist does not exist.");
            return;
        }

        try {
            playlistDAO.deletePlaylist(name);

            List<String> names = playlistDAO.getAllPlaylists()
                    .stream()
                    .map(Playlist::getName)
                    .collect(Collectors.toList());

            DeletePlaylistOutputData outputData = new DeletePlaylistOutputData(names);
            presenter.prepareSuccessView(outputData);
        } catch (Exception e) {
            presenter.prepareFailView("Could not delete playlist.");
        }
    }
}

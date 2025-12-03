package use_case.create_playlist;

import data_access.PlaylistDataAccessInterface;
import entity.Playlist;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Use case: create a new playlist with a unique name.
 */
public class CreatePlaylistInteractor implements CreatePlaylistInputBoundary {

    private final PlaylistDataAccessInterface DAO;
    private final CreatePlaylistOutputBoundary presenter;

    public CreatePlaylistInteractor(PlaylistDataAccessInterface DAO,
                                    CreatePlaylistOutputBoundary presenter) {
        this.DAO = DAO;
        this.presenter = presenter;
    }

    @Override
    public void execute(CreatePlaylistInputData inputData) {
        String name = inputData.getName();

        if (name == null || name.isBlank()) {
            presenter.prepareFailView("Playlist name cannot be empty.");
            return;
        }

        // enforce uniqueness
        Playlist existing = DAO.getPlaylist(name);
        if (existing != null) {
            presenter.prepareFailView("A playlist with that name already exists.");
            return;
        }

        // create and save
        Playlist playlist = new Playlist(name);
        DAO.savePlaylist(playlist);
        DAO.updateMedia();

        // build updated list of playlist names for the library view
        List<String> allNames = DAO.getAllPlaylists()
                .stream()
                .map(Playlist::getName)
                .collect(Collectors.toList());

        CreatePlaylistOutputData outputData =
                new CreatePlaylistOutputData(name, allNames);

        presenter.prepareSuccessView(outputData);
    }
}

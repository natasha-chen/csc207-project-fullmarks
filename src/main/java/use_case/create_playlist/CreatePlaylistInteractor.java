package use_case.create_playlist;

import data_access.PlaylistDataAccessInterface;
import entity.Playlist;

public class CreatePlaylistInteractor implements CreatePlaylistInputBoundary {

    private final PlaylistDataAccessInterface playlistGateway;
    private final CreatePlaylistOutputBoundary presenter;

    public CreatePlaylistInteractor(PlaylistDataAccessInterface gateway,
                                    CreatePlaylistOutputBoundary presenter) {
        this.playlistGateway = gateway;
        this.presenter = presenter;
    }

    @Override
    public void execute(CreatePlaylistInputData inputData) {
        String name = inputData.getName();

        // business rule: no duplicate names
        if (playlistGateway.getPlaylist(name) != null) {
            presenter.prepareFailView("Playlist already exists.");
            return;
        }

        Playlist playlist = new Playlist(name);
        playlistGateway.savePlaylist(playlist);

        presenter.prepareSuccessView(new CreatePlaylistOutputData(name));
    }
}

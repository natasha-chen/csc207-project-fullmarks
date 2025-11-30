package use_case.create_playlist;// *** come back to edit later

import data_access.PlaylistDataAcessInterface;

public class CreatePlaylistInteractor implements CreatePlaylistInputBoundary {

    private final PlaylistDataAcessInterface playlistGateway;
    private final CreatePlaylistOutputBoundary presenter;

    public CreatePlaylistInteractor(PlaylistDataAcessInterface gateway,
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

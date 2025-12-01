package main.java.use_case.modify_playlist;

import main.java.entity.Playlist;

import java.io.IOException;
import java.util.List;

public class ModifyPlaylistInteractor implements ModifyPlaylistInputBoundary {

    private final ModifyPlaylistOutputBoundary presenter;
    // TODO: private final PlaylistDataAccessInterface playlistDAO;

    public ModifyPlaylistInteractor(ModifyPlaylistOutputBoundary presenter /*, PlaylistDataAccessInterface playlistDAO*/) {
        this.presenter = presenter;
        // this.playlistDAO = playlistDAO;
    }

    @Override
    public void addSongs(String playlistName, List<String> songIds) {
        try {
            // TODO: Playlist playlist = playlistDAO.get(playlistName);
            // playlist.addSongs(songIds);   // allow duplicates
            // playlistDAO.save(playlist);

            ModifyPlaylistOutputData out =
                    new ModifyPlaylistOutputData(playlistName, songIds /* playlist.getSongIds() */);

            presenter.prepareSuccessView(out);

        } catch (IOException e) {
            presenter.prepareFailView("IO error: unable to write playlist data");
        }
    }

    @Override
    public void removeSong(String playlistName, String songId) {
        try {
            // TODO: Playlist playlist = playlistDAO.get(playlistName);
            // playlist.removeSong(songId);
            // playlistDAO.save(playlist);

            ModifyPlaylistOutputData out =
                    new ModifyPlaylistOutputData(playlistName, List.of(/* playlist.getSongIds() */));

            presenter.prepareSuccessView(out);

        } catch (IOException e) {
            presenter.prepareFailView("IO error: unable to write playlist data");
        }
    }
}

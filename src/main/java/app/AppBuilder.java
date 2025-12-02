package app;

//** imports **//

// for file access
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

// data access
import data_access.*;

// 1: library

// delete playlist
import use_case.delete_playlist.DeletePlaylistInteractor;
import interface_adapter.delete_playlist.DeletePlaylistController;
import interface_adapter.delete_playlist.DeletePlaylistPresenter;

// load playlist library
import interface_adapter.create_playlist.CreatePlaylistController;
import interface_adapter.create_playlist.CreatePlaylistPresenter;
import interface_adapter.modify_playlist.ModifyPlaylistController;
import interface_adapter.modify_playlist.ModifyPlaylistPresenter;
import use_case.create_playlist.CreatePlaylistInputBoundary;
import use_case.create_playlist.CreatePlaylistInteractor;
import use_case.create_playlist.CreatePlaylistOutputBoundary;
import use_case.modify_playlist.ModifyPlaylistInputBoundary;
import use_case.modify_playlist.ModifyPlaylistInputData;
import use_case.modify_playlist.ModifyPlaylistInteractor;
import use_case.modify_playlist.ModifyPlaylistOutputBoundary;
import use_case.playlist_library.PlaylistLibraryInputBoundary;
import use_case.playlist_library.PlaylistLibraryOutputBoundary;
import use_case.playlist_library.PlaylistLibraryInteractor;

// library view interface adapters
import interface_adapter.library.LibraryViewModel;
import interface_adapter.library.LibraryPresenter;
import interface_adapter.library.LibraryController;

// library view
import view.playlist.LibraryView;

// 2: playlist

// load playlist
import use_case.playlist.PlaylistInputBoundary;
import use_case.playlist.PlaylistOutputBoundary;
import use_case.playlist.PlaylistInteractor;

// playlist view interface adapters
import interface_adapter.playlist_view.PlaylistViewModel;
import interface_adapter.playlist_view.PlaylistPresenter;
import interface_adapter.playlist_view.PlaylistController;

// library view
import view.playlist.PlaylistView;

//
import interface_adapter.ProgressBar.ProgressController;
import interface_adapter.ProgressBar.ProgressPresenter;
import interface_adapter.ProgressBar.ProgressViewModel;
import interface_adapter.ViewManagerModel;

import interface_adapter.signup.*;
import interface_adapter.login.*;
import interface_adapter.menu.*;
import interface_adapter.download.*;
import interface_adapter.url.*;
import interface_adapter.select_for_conversion.*;
import interface_adapter.user.FileUserDataAccessObject;

import use_case.progress.ProgressInteractor;
import use_case.select_for_conversion.SelectForConversionInputBoundary;
import use_case.select_for_conversion.SelectForConversionInteractor;
import use_case.signup.*;
import use_case.login.*;
import use_case.download.*;
import use_case.url.*;

import view.*;

import javax.swing.*;
import java.awt.*;




public class AppBuilder {

    // Store these for logout reset
    private LoginViewModel loginViewModel;
    private SignupViewModel signupViewModel;

    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    public final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager =
            new ViewManager(cardPanel, cardLayout, viewManagerModel);

    public JPanel build() {

        // DATA ACCESS
        FileUserDataAccessObject userDataAccessObject =
                new FileUserDataAccessObject("users.csv");
        // Playlist data access (per-user via PathManager)
        PlaylistDataAccessObject playlistDataAccessObject =
                new PlaylistDataAccessObject();
        PlaylistDataAccessInterface playlistDAO = playlistDataAccessObject;

        // MENU VIEWMODEL (home page)
        MenuViewModel menuViewModel = new MenuViewModel();

        // LOGIN SETUP
        loginViewModel = new LoginViewModel();
        LoginPresenter loginPresenter =
                new LoginPresenter(loginViewModel, viewManagerModel,
                        menuViewModel, playlistDataAccessObject);
        LoginInteractor loginInteractor =
                new LoginInteractor(userDataAccessObject, loginPresenter);
        LoginController loginController = new LoginController(loginInteractor);
        LoginView loginView =
                new LoginView(loginController, loginViewModel, viewManagerModel);

        // SIGNUP SETUP
        signupViewModel = new SignupViewModel();
        SignupPresenter signupPresenter =
                new SignupPresenter(signupViewModel, viewManagerModel,
                        loginViewModel, playlistDAO);
        SignupInteractor signupInteractor =
                new SignupInteractor(userDataAccessObject, signupPresenter);
        SignupController signupController = new SignupController(signupInteractor);
        SignupView signupView =
                new SignupView(signupController, signupViewModel, viewManagerModel);

        // MENU
        SignupLoginMenuView signupLoginMenuView =
                new SignupLoginMenuView(viewManagerModel);

        // ** LIBRARY setup
        LibraryView libraryView = getLibraryView(playlistDAO);


        // ** PLAYLIST setup
        PlaylistViewModel playlistViewModel = new PlaylistViewModel();

        PlaylistOutputBoundary PlaylistPresenter =
                new PlaylistPresenter(playlistViewModel, viewManagerModel);

        ModifyPlaylistOutputBoundary modifyPlaylistPresenter =
                new ModifyPlaylistPresenter(playlistViewModel);

        ModifyPlaylistInputBoundary modifyPlaylistInputBoundary =
                new ModifyPlaylistInteractor(playlistDAO, modifyPlaylistPresenter);
        ModifyPlaylistController modifyPlaylistController =
                new ModifyPlaylistController(modifyPlaylistInputBoundary);
        PlaylistView playlistView =
                new PlaylistView(playlistViewModel, modifyPlaylistController,
                        playlistDAO, viewManagerModel);

// Menu view
//        MenuView menuView =
//                new MenuView(viewManagerModel, createPlaylistController);

        // PROGRESS BAR SETUP
        ProgressViewModel progressViewModel = new ProgressViewModel();
        ProgressPresenter progressPresenter = new ProgressPresenter(progressViewModel, viewManagerModel);
        ProgressInteractor progressInteractor = new ProgressInteractor(progressPresenter);

        // Controller needs downloadInteractor later
        ProgressController progressController =
                new ProgressController(null, progressInteractor);

        ProgressView progressView = new ProgressView(progressViewModel, viewManagerModel);
        progressView.setProgressController(progressController);


        // DOWNLOAD SETUP
        DownloadViewModel downloadViewModel = new DownloadViewModel();
        DownloadPresenter downloadPresenter =
                new DownloadPresenter(downloadViewModel, viewManagerModel);

        DownloadDataAccessInterface downloadDAO = new Downloader();

        DownloadInteractor downloadInteractor =
                new DownloadInteractor(downloadDAO, downloadPresenter, progressInteractor);

        progressController.setDownloadInteractor(downloadInteractor);

        DownloadController downloadController =
                new DownloadController(downloadInteractor);
        DownloadView downloadView =
                new DownloadView(downloadController, downloadViewModel, viewManagerModel, progressController);

        //TODO: CREATE PLAYLIST SETUP
//        CreatePlaylistViewModel createPlaylistViewModel = new CreatePlaylistViewModel();
//        CreatePlaylistPresenter createPlaylistPresenter =
//                new CreatePlaylistPresenter();
//        CreatePlaylistInputBoundary createPlaylistInteractor = new CreatePlaylistInteractor(CreatePlaylistPresenter);
//        CreatePlaylistController createPlaylistController = new CreatePlaylistController(createPlaylistInteractor);
//        CreatePlaylistView createPlaylistView =
//                new CreatePlaylistView();

        //URL View
        URLViewModel urlViewModel = new URLViewModel();
        // SELECT FOR CONVERSION
        SelectForConversionViewModel selectForConversionViewModel = new SelectForConversionViewModel();
        SelectForConversionPresenter selectForConversionPresenter =
                new SelectForConversionPresenter(selectForConversionViewModel, menuViewModel, viewManagerModel);
        SelectForConversionInputBoundary selectForConversionInteractor =
                new SelectForConversionInteractor(selectForConversionPresenter, new AudioConverter());
        SelectForConversionController selectForConversionController =
                new SelectForConversionController(selectForConversionInteractor);
        SelectForConversionView selectForConversionView =
                new SelectForConversionView(selectForConversionController, selectForConversionViewModel);

        downloadView.setSelectForConversionViewModel(selectForConversionViewModel);
        loginView.setSelectForConversionViewModel(selectForConversionViewModel);


        URLPresenter urlPresenter =
                new URLPresenter(urlViewModel, downloadViewModel, selectForConversionViewModel, viewManagerModel);
        URLInputBoundary urlInteractor = new URLInteractor(urlPresenter, new Fetcher());
        URLController urlController = new URLController(urlInteractor);
        URLView urlView = new URLView(urlViewModel, viewManagerModel);
        urlView.setURLController(urlController);

        // MENU VIEW (AFTER LOGIN)
        MenuView menuView = new MenuView(
                viewManagerModel,
                loginViewModel,
                signupViewModel,
                urlViewModel,
                // createPlaylistViewModel,
                menuViewModel
        );

        // CARD LAYOUT
        cardPanel.setLayout(cardLayout);

        cardPanel.add(signupLoginMenuView, "signup_login_menu");
        cardPanel.add(signupView, "signup");
        cardPanel.add(loginView, "login");
        cardPanel.add(menuView, "menu");
        cardPanel.add(urlView, "url");
        cardPanel.add(downloadView, "download");
        // cardPanel.add(createPlaylistView, "create_playlist");
        cardPanel.add(progressView, "progress");
        cardPanel.add(selectForConversionView, "select_for_conversion");
        // Library + Playlist
        cardPanel.add(libraryView, "playlist_library");
        cardPanel.add(playlistView, "playlist_view");

        // VIEW MANAGER REGISTRATION
        viewManager.addView(signupLoginMenuView, "signup_login_menu");
        viewManager.addView(signupView, "signup");
        viewManager.addView(loginView, "login");
        viewManager.addView(menuView, "menu");
        viewManager.addView(urlView, "url");
        viewManager.addView(downloadView, "download");
        // viewManager.addView(createPlaylistView, "create playlist");
        viewManager.addView(progressView, "progress");
        viewManager.addView(selectForConversionView, "select for conversion");
        // Library + Playlist
        viewManager.addView(libraryView, "playlist_library");
        viewManager.addView(playlistView, "playlist_view");

        return cardPanel;
    }

    // Initialize User Playlist and Song data (appdata)
    private Path initUserAppData(String username) {
        Path userRoot = Paths.get("appdata", username);
        Path media = userRoot.resolve("media");
        Path playlistsJson = userRoot.resolve("playlists.json");
        try {
            Files.createDirectories(media);
            if (!Files.exists(playlistsJson)) {
                Files.writeString(playlistsJson, "{}", StandardOpenOption.CREATE);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize user appdata", e);
        }
        return userRoot;  // AppBuilder will pass this into DAOs
    }

    private LibraryView getLibraryView(PlaylistDataAccessInterface playlistDAO) {
        // Shared view model for the library screen
        LibraryViewModel libraryViewModel = new LibraryViewModel();

        // LIBRARY use case wiring
        PlaylistLibraryOutputBoundary libraryPresenter =
                new LibraryPresenter(libraryViewModel);

        PlaylistLibraryInputBoundary loadLibraryInteractor =
                new PlaylistLibraryInteractor(playlistDAO, libraryPresenter);

        LibraryController libraryController =
                new LibraryController(loadLibraryInteractor);

        // CREATE PLAYLIST use case wiring (reuses same libraryViewModel)
        CreatePlaylistOutputBoundary createPlaylistPresenter =
                new CreatePlaylistPresenter(libraryViewModel, viewManagerModel);

        CreatePlaylistInputBoundary createPlaylistInteractor =
                new CreatePlaylistInteractor(playlistDAO, createPlaylistPresenter);

        CreatePlaylistController createPlaylistController =
                new CreatePlaylistController(createPlaylistInteractor);

        // DELETE PLAYLIST use case wiring (uses the same libraryViewModel)
        DeletePlaylistPresenter deletePresenter =
                new DeletePlaylistPresenter(libraryViewModel);

        DeletePlaylistInteractor deleteInteractor =
                new DeletePlaylistInteractor(playlistDAO, deletePresenter);

        DeletePlaylistController deletePlaylistController =
                new DeletePlaylistController(deleteInteractor);

        PlaylistViewModel playlistViewModel =
                new PlaylistViewModel();
        // View
        return new LibraryView(
                libraryViewModel,
                libraryController,
                deletePlaylistController,
                createPlaylistController,
                playlistViewModel,
                viewManagerModel
        );
    }

}

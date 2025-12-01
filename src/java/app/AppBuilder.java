package app;

//** imports **//

// data access
import data_access.PlaylistDataAccessInterface;
import data_access.PlaylistDataAccessObject;

// 1: library

// load playlist library
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
import data_access.Downloader;
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
        // Playlist data access, note: path initialized in DAO file
        PlaylistDataAccessInterface playlistDAO =
                new PlaylistDataAccessObject();

        // MENU VIEWMODEL (home page)
        MenuViewModel menuViewModel = new MenuViewModel();

        // LOGIN SETUP
        loginViewModel = new LoginViewModel();
        LoginPresenter loginPresenter =
                new LoginPresenter(loginViewModel, viewManagerModel, menuViewModel);
        LoginInteractor loginInteractor =
                new LoginInteractor(userDataAccessObject, loginPresenter);
        LoginController loginController = new LoginController(loginInteractor);
        LoginView loginView =
                new LoginView(loginController, loginViewModel, viewManagerModel);

        // SIGNUP SETUP
        signupViewModel = new SignupViewModel();
        SignupPresenter signupPresenter =
                new SignupPresenter(signupViewModel, viewManagerModel);
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

        PlaylistInputBoundary PlaylistInteractor =
                new PlaylistInteractor(playlistDAO, PlaylistPresenter);

        PlaylistController playlistController =
                new PlaylistController(PlaylistInteractor);

        PlaylistView playlistView =
                new PlaylistView(playlistViewModel, playlistController, viewManagerModel);




        // PROGRESS BAR SETUP
        ProgressViewModel progressViewModel = new ProgressViewModel();
        ProgressPresenter progressPresenter = new ProgressPresenter(progressViewModel);
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

        //URL View
        URLViewModel urlViewModel = new URLViewModel();
        URLPresenter urlPresenter =
                new URLPresenter(urlViewModel, downloadViewModel, viewManagerModel);
        URLInputBoundary urlInteractor = new URLInteractor(urlPresenter);
        URLController urlController = new URLController(urlInteractor);
        URLView urlView = new URLView(urlViewModel, viewManagerModel);
        urlView.setURLController(urlController);

        //TODO: CREATE PLAYLIST SETUP
//        CreatePlaylistViewModel createPlaylistViewModel = new CreatePlaylistViewModel();
//        CreatePlaylistPresenter createPlaylistPresenter =
//                new CreatePlaylistPresenter();
//        CreatePlaylistInputBoundary createPlaylistInteractor = new CreatePlaylistInteractor(CreatePlaylistPresenter);
//        CreatePlaylistController createPlaylistController = new CreatePlaylistController(createPlaylistInteractor);
//        CreatePlaylistView createPlaylistView =
//                new CreatePlaylistView();

        // SELECT FOR CONVERSION
        SelectForConversionViewModel selectForConversionViewModel = new SelectForConversionViewModel();
        SelectForConversionPresenter selectForConversionPresenter =
                new SelectForConversionPresenter(selectForConversionViewModel, viewManagerModel);
        SelectForConversionInputBoundary selectForConversionInteractor =
                new SelectForConversionInteractor(selectForConversionPresenter);
        SelectForConversionController selectForConversionController =
                new SelectForConversionController(selectForConversionInteractor);
        SelectForConversionView selectForConversionView =
                new SelectForConversionView(selectForConversionController, selectForConversionViewModel);

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

    private LibraryView getLibraryView(PlaylistDataAccessInterface playlistDAO) {
        LibraryViewModel libraryViewModel = new LibraryViewModel();

        PlaylistLibraryOutputBoundary LibraryPresenter =
                new LibraryPresenter(libraryViewModel);

        PlaylistLibraryInputBoundary loadLibraryInteractor =
                new PlaylistLibraryInteractor(playlistDAO, LibraryPresenter);

        LibraryController libraryController =
                new LibraryController(loadLibraryInteractor);

        LibraryView libraryView =
                new LibraryView(libraryViewModel, libraryController, viewManagerModel);
        return libraryView;
    }
}

package app;

import data_access.YtDlpDownloader;
import interface_adapter.ViewManagerModel;

import interface_adapter.signup.*;
import interface_adapter.login.*;
import interface_adapter.url.*;
import interface_adapter.download.*;
import interface_adapter.user.FileUserDataAccessObject;

import use_case.signup.*;
import use_case.login.*;
import use_case.download.*;

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


        // URL VIEWMODEL (home page)
        URLViewModel urlViewModel = new URLViewModel();


        // LOGIN SETUP
        loginViewModel = new LoginViewModel();
        LoginPresenter loginPresenter =
                new LoginPresenter(loginViewModel, viewManagerModel, urlViewModel);
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


        // DOWNLOAD SETUP
        DownloadViewModel downloadViewModel = new DownloadViewModel();
        DownloadPresenter downloadPresenter =
                new DownloadPresenter(downloadViewModel, viewManagerModel);

        // your yt-dlp executor DAO
        DownloadDataAccessInterface downloadDAO = new YtDlpDownloader();

        DownloadInteractor downloadInteractor =
                new DownloadInteractor(downloadDAO, downloadPresenter);
        DownloadController downloadController =
                new DownloadController(downloadInteractor);
        DownloadView downloadView =
                new DownloadView(downloadController, downloadViewModel, viewManagerModel);


        // MENU
        SignupLoginMenuView menuView =
                new SignupLoginMenuView(viewManagerModel);


        // URL VIEW (AFTER LOGIN)
        URLView urlView = new URLView(
                urlViewModel,
                viewManagerModel,
                loginViewModel,
                signupViewModel,
                downloadViewModel
        );


        // CARD LAYOUT
        cardPanel.setLayout(cardLayout);

        cardPanel.add(menuView, "signup_login_menu");
        cardPanel.add(signupView, "signup");
        cardPanel.add(loginView, "login");
        cardPanel.add(urlView, "url");
        cardPanel.add(downloadView, "download");


        // VIEW MANAGER REGISTRATION
        viewManager.addView(menuView, "signup_login_menu");
        viewManager.addView(signupView, "signup");
        viewManager.addView(loginView, "login");
        viewManager.addView(urlView, "url");
        viewManager.addView(downloadView, "download");

        return cardPanel;
    }
}

package app;

import interface_adapter.ViewManagerModel;

import interface_adapter.signup.SignupViewModel;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupController;

import interface_adapter.login.LoginViewModel;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginController;

import interface_adapter.url.URLViewModel;
import interface_adapter.user.FileUserDataAccessObject;
import interface_adapter.user.InMemoryUserDataAccessObject;

import use_case.signup.*;
import use_case.login.*;

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
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    public JPanel build() {

        // DATA ACCESS OBJECT
        FileUserDataAccessObject userDataAccessObject =
                new FileUserDataAccessObject("users.csv");


        // URL VIEWMODEL
        URLViewModel urlViewModel = new URLViewModel();


        // -------- LOGIN SETUP --------
        loginViewModel = new LoginViewModel();   // <-- store in field
        LoginPresenter loginPresenter =
                new LoginPresenter(loginViewModel, viewManagerModel, urlViewModel);
        LoginInputBoundary loginInteractor =
                new LoginInteractor(userDataAccessObject, loginPresenter);
        LoginController loginController = new LoginController(loginInteractor);

        LoginView loginView =
                new LoginView(loginController, loginViewModel, viewManagerModel);


        // -------- SIGNUP SETUP --------
        signupViewModel = new SignupViewModel();  // <-- store in field
        SignupPresenter signupPresenter =
                new SignupPresenter(signupViewModel, viewManagerModel);
        SignupInputBoundary signupInteractor =
                new SignupInteractor(userDataAccessObject, signupPresenter);
        SignupController signupController = new SignupController(signupInteractor);

        SignupView signupView =
                new SignupView(signupController, signupViewModel, viewManagerModel);


        // -------- MENU --------
        SignupLoginMenuView menuView = new SignupLoginMenuView(viewManagerModel);


        // -------- URL VIEW (AFTER LOGIN) --------
        // Pass both ViewModels so logout can reset them.
        URLView urlView =
                new URLView(urlViewModel, viewManagerModel, loginViewModel, signupViewModel);


        // -------- CARD SETUP --------
        cardPanel.setLayout(cardLayout);

        cardPanel.add(menuView, "signup_login_menu");
        cardPanel.add(signupView, "signup");
        cardPanel.add(loginView, "login");
        cardPanel.add(urlView, "url");


        // -------- VIEW MANAGER --------
        viewManager.addView(menuView, "signup_login_menu");
        viewManager.addView(signupView, "signup");
        viewManager.addView(loginView, "login");
        viewManager.addView(urlView, "url");

        return cardPanel;
    }
}

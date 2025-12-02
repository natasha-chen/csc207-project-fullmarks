package interface_adapter.login;

import data_access.PlaylistDataAccessObject;
import data_access.PathManager;
import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * Presenter for the login use case.
 *
 * <p>Formats login success and failure responses for the LoginViewModel.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MenuViewModel menuViewModel;
    private final PlaylistDataAccessObject playlistDAO;

    public LoginPresenter(LoginViewModel loginViewModel,
                          ViewManagerModel viewManagerModel,
                          MenuViewModel menuViewModel,
                          PlaylistDataAccessObject playlistDAO) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.menuViewModel = menuViewModel;
        this.playlistDAO = playlistDAO;
    }

    @Override
    public void prepareSuccessView(LoginOutputData outputData) {

        String username = outputData.getUsername();

        // Update MenuViewModel with logged-in user's username
        MenuState state = menuViewModel.getState();
        state.setUsername(username);
        menuViewModel.setState(state);
        menuViewModel.firePropertyChanged();

        // Save username globally and init appdata
        PathManager.setLoggedInUsername(username);
        PathManager.initUserAppData();
        playlistDAO.reloadForCurrentUser();

        // Now switch the view
        viewManagerModel.setActiveView("menu");
        viewManagerModel.firePropertyChanged();
        loginViewModel.setUsername(username);
    }

    @Override
    public void prepareFailView(String errorMessage, String username) {
        LoginState state = loginViewModel.getState();
        state.setError(errorMessage);
        state.setUsername(username);

        loginViewModel.setState(state);
        loginViewModel.firePropertyChanged();
    }
}

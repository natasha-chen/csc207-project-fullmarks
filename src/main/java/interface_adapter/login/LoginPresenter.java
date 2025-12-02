package interface_adapter.login;

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
    private final MenuViewModel menuViewModel;   // NEW

    public LoginPresenter(LoginViewModel loginViewModel,
                          ViewManagerModel viewManagerModel,
                          MenuViewModel menuViewModel) {   // UPDATED
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.menuViewModel = menuViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData outputData) {

        // Update MenuViewModel with logged-in user's username
        MenuState state = menuViewModel.getState();
        state.setUsername(outputData.getUsername());
        PathManager.setLoggedInUsername(outputData.getUsername());
        menuViewModel.setState(state);
        menuViewModel.firePropertyChanged();

        // Now switch the view
        viewManagerModel.setActiveView("menu");
        viewManagerModel.firePropertyChanged();
        loginViewModel.setUsername(outputData.getUsername());
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

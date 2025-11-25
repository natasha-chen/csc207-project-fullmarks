package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.url.URLState;
import interface_adapter.url.URLViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final URLViewModel urlViewModel;   // NEW

    public LoginPresenter(LoginViewModel loginViewModel,
                          ViewManagerModel viewManagerModel,
                          URLViewModel urlViewModel) {   // UPDATED
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.urlViewModel = urlViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData outputData) {

        // Update URLViewModel with logged-in user's username
        URLState state = urlViewModel.getState();
        state.setUsername(outputData.getUsername());
        urlViewModel.setState(state);
        urlViewModel.firePropertyChanged();

        // Now switch the view
        viewManagerModel.setActiveView("url");
        viewManagerModel.firePropertyChanged();
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

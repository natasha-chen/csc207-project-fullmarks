package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;
import data_access.PathManager;
import data_access.PlaylistDataAccessInterface;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

/**
 * Presenter for the Signup use case.
 *
 * <p>This class formats the results of the SignupInteractor and updates the
 * SignupViewModel accordingly. It also notifies the ViewManagerModel when a
 * successful signup requires the UI to switch screens.
 */
public class SignupPresenter implements SignupOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final PlaylistDataAccessInterface playlistDAO;

    /**
     * Constructs a SignupPresenter with the given view model and view manager.
     *
     * @param signupViewModel the view model representing the signup screen state
     * @param viewManagerModel the model responsible for managing which view is active
     */
    public SignupPresenter(SignupViewModel signupViewModel,
                           ViewManagerModel viewManagerModel,
                           LoginViewModel loginViewModel,
                           PlaylistDataAccessInterface playlistDAO) {
        this.signupViewModel = signupViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.playlistDAO = playlistDAO;
    }

    /**
     * Prepares the success view after successful account creation.
     *
     * @param outputData the data produced by the signup use case
     */
    @Override
    public void prepareSuccessView(SignupOutputData outputData) {
        String username = outputData.getUsername();

        // 1) Initialize user appdata so their folder exists immediately after signup
        PathManager.setCurrentUsername(username);
        PathManager.initUserAppData();
        playlistDAO.reloadForCurrentUser();

        // 2) Prefill login screen with the new username
        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(username);
        loginState.setError(null);
        loginViewModel.setState(loginState);
        loginViewModel.setUsername(username);
        loginViewModel.firePropertyChanged();

        // 3) Clear any signup errors
        SignupState signupState = signupViewModel.getState();
        signupState.setError(null);
        signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged();

        // 4) Go back to login view
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
//    public void prepareSuccessView(SignupOutputData outputData) {
//        signupViewModel.setState(new SignupState());
//        signupViewModel.firePropertyChanged();
//
//        viewManagerModel.setActiveView("signup_login_menu");
//        viewManagerModel.firePropertyChanged();
//    }

    /**
     * Prepares the failure view when signup cannot be completed.
     *
     * @param errorMessage the explanation of why signup failed
     */
    @Override
    public void prepareFailView(String errorMessage, String username) {
        SignupState state = signupViewModel.getState();
        state.setError(errorMessage);
        state.setUsername(username);

        signupViewModel.setState(state);
        signupViewModel.firePropertyChanged();
    }
}

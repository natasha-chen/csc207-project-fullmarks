package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

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

    /**
     * Constructs a SignupPresenter with the given view model and view manager.
     *
     * @param signupViewModel the view model representing the signup screen state
     * @param viewManagerModel the model responsible for managing which view is active
     */
    public SignupPresenter(SignupViewModel signupViewModel,
                           ViewManagerModel viewManagerModel) {
        this.signupViewModel = signupViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the success view after successful account creation.
     *
     * @param outputData the data produced by the signup use case
     */
    @Override
    public void prepareSuccessView(SignupOutputData outputData) {
        signupViewModel.setState(new SignupState());
        signupViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("signup_login_menu");
        viewManagerModel.firePropertyChanged();
    }

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

package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

public class SignupPresenter implements SignupOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final ViewManagerModel viewManagerModel;

    public SignupPresenter(SignupViewModel signupViewModel,
                           ViewManagerModel viewManagerModel) {
        this.signupViewModel = signupViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData outputData) {
        signupViewModel.setState(new SignupState());
        signupViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("signup_login_menu");
        viewManagerModel.firePropertyChanged();
    }


    @Override
    public void prepareFailView(String errorMessage, String username) {
        SignupState state = signupViewModel.getState();
        state.setError(errorMessage);
        state.setUsername(username);

        signupViewModel.setState(state);
        signupViewModel.firePropertyChanged();
    }
}

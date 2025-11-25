package view;
import interface_adapter.url.URLViewModel;

import interface_adapter.url.URLViewModel;
import interface_adapter.url.URLState;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.signup.SignupState;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class URLView extends JPanel implements PropertyChangeListener {

    private final URLViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    // added these:
    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;

    private JLabel usernameLabel = new JLabel("");
    private JButton logoutButton = new JButton("Logout");

    public URLView(URLViewModel viewModel,
                   ViewManagerModel viewManagerModel,
                   LoginViewModel loginViewModel,
                   SignupViewModel signupViewModel) {

        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;

        viewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());

        // Display username
        JPanel header = new JPanel();
        header.add(usernameLabel);

        // ---- Logout button ----
        logoutButton.addActionListener(e -> {

            // 1. Reset LOGIN state
            loginViewModel.setState(new LoginState());
            loginViewModel.firePropertyChanged();

            // 2. Reset SIGNUP state
            signupViewModel.setState(new SignupState());
            signupViewModel.firePropertyChanged();

            // 3. Switch back to menu
            viewManagerModel.setActiveView("signup_login_menu");
            viewManagerModel.firePropertyChanged();
        });

        header.add(logoutButton);
        this.add(header, BorderLayout.NORTH);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        URLState state = viewModel.getState();

        if (state.getUsername() != null) {
            usernameLabel.setText("Logged in as: " + state.getUsername());
        }
    }
}

package view;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.select_for_conversion.SelectForConversionViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.url.URLViewModel;
import interface_adapter.url.URLState;
import interface_adapter.create_playlist.CreatePlaylistViewModel;
import interface_adapter.create_playlist.CreatePlaylistState;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MenuView extends JPanel implements PropertyChangeListener {

    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;
    private final URLViewModel urlViewModel;
    // private final CreatePlaylistViewModel createPlaylistViewModel;
    private final MenuViewModel viewModel;

    private JLabel usernameLabel = new JLabel("");


    private JButton urlButton = new JButton("Download from URL");
    private JButton playlistButton = new JButton("Playlist Library");
    private JButton logoutButton = new JButton("Logout");

    public MenuView(ViewManagerModel viewManagerModel,
                    LoginViewModel loginViewModel,
                    SignupViewModel signupViewModel,
                    URLViewModel urlViewModel,
                    // CreatePlaylistViewModel createPlaylistViewModel,
                    MenuViewModel menuViewModel) {

        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
        this.urlViewModel = urlViewModel;
        // this.createPlaylistViewModel = createPlaylistViewModel;
        this.viewModel = menuViewModel;

        viewModel.addPropertyChangeListener(this);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        this.add(usernameLabel, gbc);
        gbc.gridy++;
        this.add(usernameLabel, gbc);
        gbc.gridy++;
        this.add(urlButton, gbc);
        gbc.gridy++;
        this.add(playlistButton, gbc);
        gbc.gridy++;
        this.add(logoutButton, gbc);


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

        // ---- URL button ----
        urlButton.addActionListener(e -> {
            // Switch to url view
            viewManagerModel.setActiveView(urlViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        });

        // ---- Playlist button ----
        playlistButton.addActionListener(e -> {

            // TODO: playlistViewModel is updated with files corresponding to username
            //  would this be done by having the same prefix and changing the last part of the filepath?
//             playlistViewModel.getState().setFilePath();
//             playlistViewModel.firePropertyChanged();

            // 3. Switch to create playlist view
//            viewManagerModel.setActiveView(createPlaylistViewModel.getViewName());
//            viewManagerModel.firePropertyChanged();
        });
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        MenuState state = this.viewModel.getState();

        if (state.getUsername() != null) {
            usernameLabel.setText("Logged in as: " + state.getUsername());
        }
    }
}
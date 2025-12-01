package view;

import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;

/**
 * The initial menu view that allows the user to choose between signing up
 * for a new account or logging in to an existing one.
 *
 * <p>This view provides two buttons: “Sign Up” and “Log In”. When the user
 * selects one of these options, the view notifies the ViewManagerModel so the
 * active screen can be switched accordingly.
 */
public class SignupLoginMenuView extends JPanel {

    /**
     * Constructs a SignupLoginMenuView with the given ViewManagerModel.
     *
     * <p>The constructor initializes the layout, creates the buttons for
     * navigation, and attaches the listeners that trigger view changes.
     *
     * @param viewManagerModel the model responsible for switching between views
     */
    public SignupLoginMenuView(ViewManagerModel viewManagerModel) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton signupButton = new JButton("Sign Up");
        JButton loginButton = new JButton("Log In");

        signupButton.addActionListener(e -> {
            viewManagerModel.setActiveView("signup");
            viewManagerModel.firePropertyChanged();
        });

        loginButton.addActionListener(e -> {
            viewManagerModel.setActiveView("login");
            viewManagerModel.firePropertyChanged();
        });

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(signupButton, gbc);

        gbc.gridy = 1;
        this.add(loginButton, gbc);
    }
}

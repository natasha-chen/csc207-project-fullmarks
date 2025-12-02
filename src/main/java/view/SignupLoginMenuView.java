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

        JLabel title = new JLabel("Youtube to MP3/4");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // Buttons
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

        // Spacing
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.gridx = 0;

        // ---------- ADD TITLE ----------
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(title, gbc);

        // ---------- ADD BUTTONS ----------
        gbc.gridy = 1;
        this.add(signupButton, gbc);

        gbc.gridy = 2;
        this.add(loginButton, gbc);
    }
}

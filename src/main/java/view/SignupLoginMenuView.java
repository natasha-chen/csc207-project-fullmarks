package view;

import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code SignupLoginMenuView} represents the initial landing screen of the application.
 * <p>
 * This view displays a title along with two buttons: "Sign Up" and "Log In".
 * Clicking either button updates the {@link ViewManagerModel} to switch to the
 * appropriate view.
 */
public class SignupLoginMenuView extends JPanel {

    /**
     * Constructs the signup/login menu view. This screen appears when the application starts
     * and allows the user to navigate to either the signup flow or login flow.
     *
     * @param viewManagerModel the model responsible for managing the currently active view
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

package view;

import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;

public class SignupLoginMenuView extends JPanel {

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

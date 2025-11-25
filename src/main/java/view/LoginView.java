package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements PropertyChangeListener {

    private final JTextField usernameField = new JTextField(15);
    private final JPasswordField passwordField = new JPasswordField(15);
    private final JLabel errorLabel = new JLabel(" ");

    public LoginView(LoginController controller,
                     LoginViewModel viewModel,
                     ViewManagerModel viewManagerModel) {

        viewModel.addPropertyChangeListener(this);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);

        JLabel title = new JLabel("Log In");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18));

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        this.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        this.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        this.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        this.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        this.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        errorLabel.setForeground(Color.RED);
        this.add(errorLabel, gbc);

        JButton loginButton = new JButton("Log In");
        loginButton.addActionListener(e -> {
            controller.execute(
                    usernameField.getText(),
                    new String(passwordField.getPassword())
            );
        });

        gbc.gridy = 4;
        this.add(loginButton, gbc);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            // 1. Reset model state
            LoginState emptyState = new LoginState();
            viewModel.setState(emptyState);
            viewModel.firePropertyChanged();

            // 2. Clear UI fields explicitly
            usernameField.setText("");
            passwordField.setText("");
            errorLabel.setText("");

            // 3. Switch back to menu
            viewManagerModel.setActiveView("signup_login_menu");
            viewManagerModel.firePropertyChanged();
        });



        gbc.gridy = 5;
        this.add(backButton, gbc);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        if (state == null) return;

        usernameField.setText(state.getUsername() == null ? "" : state.getUsername());
        errorLabel.setText(state.getError() == null ? "" : state.getError());

        // always clear password on update
        passwordField.setText("");
    }
}

package view;

import interface_adapter.signup.*;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View model for the Signup view.
 *
 * <p>Stores the current state and error messages visible to the user.
 */
public class SignupView extends JPanel implements PropertyChangeListener {

    private final SignupViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    private final JTextField usernameField = new JTextField(15);
    private final JPasswordField passwordField = new JPasswordField(15);
    private final JPasswordField repeatField = new JPasswordField(15);
    private final JLabel errorLabel = new JLabel(" ");

    public SignupView(SignupController controller,
                      SignupViewModel viewModel,
                      ViewManagerModel viewManagerModel) {

        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;

        viewModel.addPropertyChangeListener(this);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        JLabel title = new JLabel("Create Account");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22));

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        this.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        this.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        this.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        this.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        this.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        this.add(new JLabel("Repeat Password:"), gbc);

        gbc.gridx = 1;
        this.add(repeatField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        errorLabel.setForeground(Color.RED);
        this.add(errorLabel, gbc);

        JButton signupButton = new JButton("Create Account");
        signupButton.addActionListener(e -> controller.execute(
                usernameField.getText(),
                new String(passwordField.getPassword()),
                new String(repeatField.getPassword())
        ));
        passwordField.addActionListener(e -> controller.execute(
                usernameField.getText(),
                new String(passwordField.getPassword()),
                new String(repeatField.getPassword())
        ));
        repeatField.addActionListener(e -> controller.execute(
                usernameField.getText(),
                new String(passwordField.getPassword()),
                new String(repeatField.getPassword())
        ));
        usernameField.addActionListener(e -> controller.execute(
                usernameField.getText(),
                new String(passwordField.getPassword()),
                new String(repeatField.getPassword())
        ));

        gbc.gridy = 5;
        this.add(signupButton, gbc);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            viewModel.setState(new SignupState());
            viewModel.firePropertyChanged();

            usernameField.setText("");
            passwordField.setText("");
            repeatField.setText("");
            errorLabel.setText("");

            viewManagerModel.setActiveView("signup_login_menu");
            viewManagerModel.firePropertyChanged();
        });

        gbc.gridy = 6;
        this.add(backButton, gbc);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
        if (state == null) return;

        usernameField.setText(state.getUsername() == null ? "" : state.getUsername());
        errorLabel.setText(state.getError() == null ? "" : state.getError());

        // ALWAYS clear both password fields
        passwordField.setText("");
        repeatField.setText("");

    }
}

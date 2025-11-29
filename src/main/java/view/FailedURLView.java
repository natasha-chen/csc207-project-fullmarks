package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.failed_url.FailedURLState;
import interface_adapter.failed_url.FailedURLViewModel;
import interface_adapter.url.URLViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View for the Failed URL use case.
 */
public class FailedURLView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "failed url";
    private final FailedURLViewModel failedURLViewModel;
    private final URLViewModel urlViewModel;
    private final ViewManagerModel viewManagerModel;

    private final JLabel errorMessageLabel = new JLabel();
    private final JButton tryAgainButton;

    public FailedURLView(FailedURLViewModel failedURLViewModel,
                         URLViewModel urlViewModel,
                         ViewManagerModel viewManagerModel) {
        this.failedURLViewModel = failedURLViewModel;
        this.urlViewModel = urlViewModel;
        this.viewManagerModel = viewManagerModel;
        this.failedURLViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(FailedURLViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 16));

        errorMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorMessageLabel.setForeground(Color.RED);

        final JPanel buttons = new JPanel();
        tryAgainButton = new JButton(FailedURLViewModel.TRY_AGAIN_BUTTON_LABEL);
        buttons.add(tryAgainButton);

        tryAgainButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(tryAgainButton)) {
                            // Clear the error message in failed view
                            final FailedURLState failedState = failedURLViewModel.getState();
                            failedState.setErrorMessage("");

                            // Switch back to URL view
                            viewManagerModel.setActiveView(urlViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createVerticalStrut(20));
        this.add(title);
        this.add(Box.createVerticalStrut(10));
        this.add(errorMessageLabel);
        this.add(Box.createVerticalStrut(20));
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final FailedURLState state = (FailedURLState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(FailedURLState state) {
        errorMessageLabel.setText(state.getErrorMessage());
    }

    public String getViewName() {
        return viewName;
    }
}
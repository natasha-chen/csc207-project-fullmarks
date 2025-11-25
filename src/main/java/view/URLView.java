package main.java.view;

import main.java.interface_adapter.url.URLController;
import main.java.interface_adapter.url.URLState;
import main.java.interface_adapter.url.URLViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
/**
 * The View for when the user pastes the URL of choice.
 */

public class URLView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "url";
    private final URLViewModel urlViewModel;
    private URLController urlController;

    private final JTextField urlInputField = new JTextField(20);
    private final JButton enterButton;

    public URLView(URLViewModel urlViewModel) {
        this.urlViewModel = urlViewModel;
        this.urlViewModel.addPropertyChangeListener(this);

        // Set up the title
        final JLabel title = new JLabel(URLViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set up the URL input field
        final LabelTextPanel urlInfo = new LabelTextPanel(
                new JLabel(URLViewModel.URL_LABEL), urlInputField);

        // Set up the Enter button
        final JPanel buttons = new JPanel();
        enterButton = new JButton(URLViewModel.ENTER_BUTTON_LABEL);
        buttons.add(enterButton);

        // Add action listener to button
        enterButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(enterButton)) {
                            final URLState currentState = urlViewModel.getState();
                            urlController.execute(currentState.getUrl());
                        }
                    }
                }
        );

        // Add key listener for Enter key
        urlInputField.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final URLState currentState = urlViewModel.getState();
                        urlController.execute(currentState.getUrl());
                    }
                }
        );

        // Layout
        // Add document listener to update state when text changes
        urlInputField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void documentListenerHelper() {
                final URLState currentState = urlViewModel.getState();
                currentState.setUrl(urlInputField.getText());
            }

            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(urlInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final URLState state = (URLState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(URLState state) {
        urlInputField.setText(state.getUrl());
    }

    public String getViewName() {
        return viewName;
    }

    public void setURLController(URLController urlController) {
        this.urlController = urlController;
    }
}
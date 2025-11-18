package view;
import interface_adapter.url.URLViewModel;

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

    private final URLViewModel urlViewModel;

    JTextField textField = new JTextField(30); // 30 columns wide

    public URLView(URLViewModel urlViewModel) {
        this.urlViewModel = urlViewModel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

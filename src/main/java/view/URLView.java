package view;

import interface_adapter.url.URLController;
import interface_adapter.url.URLViewModel;

import javax.swing.*;
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

    private final JTextField urlInputField = new JTextField(20);
    private final JButton enterURL;

    private URLController urlController = null;

    public URLView(URLViewModel urlViewModel) {
        this.urlViewModel = urlViewModel;
        this.urlViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Enter URL Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel urlInput = new LabelTextPanel(
                new JLabel("URL"), urlInputField);

        final JPanel buttons = new JPanel();
        enterURL = new JButton("url");
        buttons.add(enterURL);

//        enterURL.addActionListener( //pressing enter will take the view from URL to InvalidURLView, ConvertView, or PlaylistView
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(enterURL)) {
//                            final URLState currentState = URLViewModel.getState();
//                        }
//                    }
//                }
//        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

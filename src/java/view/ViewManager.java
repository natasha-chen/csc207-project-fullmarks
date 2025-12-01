package view;

import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View Manager for the program. It listens for property change events
 * in the ViewManagerModel and updates which View should be visible.
 */
public class ViewManager implements PropertyChangeListener {

    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private final ViewManagerModel viewManagerModel;

    public ViewManager(JPanel cardPanel, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;

        // Listen for active view changes
        this.viewManagerModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("activeView".equals(evt.getPropertyName())) {
            String viewName = (String) evt.getNewValue();
            cardLayout.show(cardPanel, viewName);
        }
    }

    /**
     * Register a new view with CardLayout.
     */
    public void addView(JComponent view, String name) {
        cardPanel.add(view, name);
    }
}
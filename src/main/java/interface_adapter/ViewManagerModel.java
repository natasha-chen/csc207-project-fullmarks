package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewManagerModel tracks which view is currently active in the application.
 *
 * <p>Views listen for changes to this model and update their visibility or content
 * accordingly. When a view is switched, this model fires a property change event
 * with the name "activeView" and the value of the newly selected view.
 *
 * <p>This class is part of the interface-adapter layer of the Clean Architecture
 * structure and acts as a central coordinator for screen transitions.
 */
public class ViewManagerModel {

    private String activeViewName;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void setActiveView(String viewName) {
        this.activeViewName = viewName;
        support.firePropertyChange("activeView", null, viewName);
    }

    public String getActiveViewName() {
        return activeViewName;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void firePropertyChanged() {
        support.firePropertyChange("activeView", null, activeViewName);
    }
}
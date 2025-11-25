package main.java.interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Model for the View Manager. This manages which view is currently active.
 */
public class ViewManagerModel {
    private String state = "";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void firePropertyChanged() {
        this.support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }
}
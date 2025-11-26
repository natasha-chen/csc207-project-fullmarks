package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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

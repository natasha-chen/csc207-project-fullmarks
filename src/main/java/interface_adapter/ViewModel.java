package interface_adapter;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class ViewModel {

    protected final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // every view model must specify its own name
    public abstract String getViewName();

    // every view model must expose its state
    public abstract Object getState();

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void firePropertyChanged() {
        // notify ViewManager and the corresponding ViewModel listeners
        support.firePropertyChange(getViewName(), null, getState());
    }
}

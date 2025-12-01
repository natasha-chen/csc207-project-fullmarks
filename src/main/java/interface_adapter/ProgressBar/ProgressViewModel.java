package interface_adapter.ProgressBar;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
/** This allows a class to "listen" to changes in the state of another class. **/

/**
 * The ViewModel for the Signup View.
 */


/** Manage changes to ProgressState and notify the View */
public class ProgressViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private static final String VIEW_NAME = "progress";

    private ProgressState state = new ProgressState();

    public ProgressState getState() {
        return state;
    }

    public void setState(ProgressState state) {
        this.state = state;
        firePropertyChanged();
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

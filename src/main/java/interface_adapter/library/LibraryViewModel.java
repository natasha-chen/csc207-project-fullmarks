package main.java.interface_adapter.library;

import interface_adapter.ViewModel;

public class LibraryViewModel extends ViewModel {

    private final LibraryState state = new LibraryState();

    public LibraryViewModel() {
        super();   // or just delete this constructor body entirely
    }

    // REQUIRED by ViewModel
    @Override
    public String getViewName() {
        return "library";
    }

    // REQUIRED by ViewModel
    @Override
    public LibraryState getState() {
        return state;
    }

    @Override
    public void firePropertyChanged() {
        // 'support' comes from your abstract ViewModel
        support.firePropertyChange("state", null, this.state);
    }

    // Convenience setters for presenter

    public void setPlaylistNames(java.util.List<String> playlistNames) {
        state.setPlaylistNames(playlistNames);
        firePropertyChanged();
    }

    public void setErrorMessage(String errorMessage) {
        state.setErrorMessage(errorMessage);
        firePropertyChanged();
    }
}

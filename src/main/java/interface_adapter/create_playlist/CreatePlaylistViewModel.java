package interface_adapter.create_playlist;

import interface_adapter.ViewModel;

public class CreatePlaylistViewModel extends ViewModel{
    public static final String VIEW_NAME = "create playlist";
    private CreatePlaylistState state = new CreatePlaylistState();

    @Override
    public CreatePlaylistState getState() {
        return state;
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }
}

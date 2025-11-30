package interface_adapter.create_playlist;

import interface_adapter.ViewModel;
import interface_adapter.select_for_conversion.SelectForConversionState;

public class CreatePlaylistViewModel extends ViewModel{
    public static final String VIEW_NAME = "create playlist";
    private SelectForConversionState state = new SelectForConversionState();

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }
}

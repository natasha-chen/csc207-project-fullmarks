package interface_adapter.select_for_conversion;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Select for Conversion View.
 */
public class SelectForConversionViewModel extends ViewModel<SelectForConversionState> {

    public static final String TITLE_LABEL = "Select For Conversion";
    // if further elaboration needed, add additional final here and integrate into PlaylistView
    public static final String VIDEO_TITLE_LABEL = "Video";
    public static final String MP3_LABEL = "MP3";

    public static final String SKIP_BUTTON_LABEL = "Skip";
    public static final String BEGIN_BUTTON_LABEL = "Begin";

    public SelectForConversionViewModel() {
        super("Select Videos for Conversion");
        setState(new SelectForConversionState());
    }

}

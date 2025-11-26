package interface_adapter.select_for_conversion;

import interface_adapter.ViewModel;
import interface_adapter.signup.SignupState;

/**
 * The ViewModel for the Select for Conversion View.
 */
public class SelectForConversionViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Select For Conversion";
    // if further elaboration needed, add additional final here and integrate into SelectForConversionView
    public static final String VIDEO_TITLE_LABEL = "Video";
    public static final String MP3_LABEL = "MP3";

    public static final String SKIP_BUTTON_LABEL = "Skip";
    public static final String BEGIN_BUTTON_LABEL = "Begin";

    public static final String VIEW_NAME = "select for conversion";
    private SelectForConversionState state = new SelectForConversionState();

    public SelectForConversionViewModel() {}

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

    public SelectForConversionState getState() {
        return state;
    }

    public void setState(SelectForConversionState state) {this.state = state;}
}

package interface_adapter.select_for_conversion;

import interface_adapter.ViewManagerModel;
import interface_adapter.url.URLViewModel;
import use_case.select_for_conversion.SelectForConversionOutputBoundary;
import use_case.select_for_conversion.SelectForConversionOutputData;

/**
 * The Presenter for the Select for Conversion Use Case.
 */
public class SelectForConversionPresenter implements SelectForConversionOutputBoundary {

    private final SelectForConversionViewModel selectForConversionViewModel;
    private final URLViewModel urlViewModel;
//    private final ProgressViewModel progressViewModel;
    private final ViewManagerModel viewManagerModel;

    public SelectForConversionPresenter(SelectForConversionViewModel selectForConversionViewModel,
                                        URLViewModel urlViewModel,
//                                        ProgressViewModel progressViewModel,
                                        ViewManagerModel viewManagerModel) {
        this.selectForConversionViewModel = selectForConversionViewModel;
        this.urlViewModel = urlViewModel;
//        this.progressViewModel = progressViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SelectForConversionOutputData outputData) {
        // Switch to Progress
        System.out.println("SuccessView triggered.");
    }

    @Override
    public void prepareFailView(String error) {
        final SelectForConversionState selectForConversionState = selectForConversionViewModel.getState();
        selectForConversionState.setConversionError(error);
        selectForConversionViewModel.firePropertyChanged();
    }

    @Override
    public void switchToUrlView() {
        this.viewManagerModel.setActiveView(this.urlViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}

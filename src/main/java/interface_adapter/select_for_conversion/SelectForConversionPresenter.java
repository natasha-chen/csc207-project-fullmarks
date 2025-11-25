package interface_adapter.select_for_conversion;

import interface_adapter.ViewManagerModel;
import use_case.select_for_conversion.SelectForConversionOutputBoundary;
import use_case.select_for_conversion.SelectForConversionOutputData;

/**
 * The Presenter for the Select for Conversion Use Case.
 */
public class SelectForConversionPresenter implements SelectForConversionOutputBoundary {

    private final SelectForConversionViewModel selectForConversionViewModel;
//    private final UrlViewModel urlViewModel;
//    private final ProgressViewModel progressViewModel;
    private final ViewManagerModel viewManagerModel;

    public SelectForConversionPresenter(SelectForConversionViewModel selectForConversionViewModel,
                                        // UrlViewModel urlViewModel
                                        // ProgressViewModel progressViewModel,
                                        ViewManagerModel viewManagerModel) {
        this.selectForConversionViewModel = selectForConversionViewModel;
        // this.urlViewModel = urlViewModel;
        // this.progressViewModel = progressViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SelectForConversionOutputData outputData) {
        // Switch to Progress
        System.out.println("SuccessView triggered.");
        System.out.println("final ProgressState progressState = progressViewModel.getState();");
        System.out.println("progressState.setSelectedVideos(selectForConversionOutputData.getSelectedVideos());)");
        System.out.println("progressViewModel.firePropertyChange();");
        System.out.println("viewManagerModel.setState(ProgressViewModel.getViewName());");
        System.out.println("viewManagerModel.firePropertyChange();");
        System.out.println("Note to self: create similar texts for test drivers.");
    }

    @Override
    public void prepareFailView(String error) {
        final SelectForConversionState selectForConversionState = selectForConversionViewModel.getState();
        selectForConversionState.setConversionError(error);
        selectForConversionViewModel.firePropertyChange();
    }

    @Override
    public void switchToUrlView() {
        System.out.println("viewManagerModel.setState(urlViewModel.getViewName());");
        System.out.println("viewManagerModel.firePropertyChange();");

    }
}

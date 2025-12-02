package interface_adapter.select_for_conversion;

import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuViewModel;
import use_case.select_for_conversion.SelectForConversionOutputBoundary;
import use_case.select_for_conversion.SelectForConversionOutputData;

/**
 * The Presenter for the Select for Conversion Use Case.
 */
public class SelectForConversionPresenter implements SelectForConversionOutputBoundary {

    private final SelectForConversionViewModel selectForConversionViewModel;
    private final MenuViewModel menuViewModel;
    private final ViewManagerModel viewManagerModel;

    public SelectForConversionPresenter(SelectForConversionViewModel selectForConversionViewModel,
                                        MenuViewModel menuViewModel,
                                        ViewManagerModel viewManagerModel) {
        this.selectForConversionViewModel = selectForConversionViewModel;
        this.menuViewModel = menuViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SelectForConversionOutputData outputData) {
    }

    @Override
    public void prepareFailView(String error) {
        final SelectForConversionState selectForConversionState = selectForConversionViewModel.getState();
        selectForConversionState.setConversionError(error);
        selectForConversionViewModel.firePropertyChanged();
    }

    @Override
    public void switchToMenuView() {
        this.viewManagerModel.setActiveView(this.menuViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}

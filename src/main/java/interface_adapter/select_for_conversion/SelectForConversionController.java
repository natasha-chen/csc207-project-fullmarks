package interface_adapter.select_for_conversion;

import custom_datatype.VideoData;
import use_case.select_for_conversion.SelectForConversionInputBoundary;
import use_case.select_for_conversion.SelectForConversionInputData;

import java.util.List;

/**
 * Controller for the Select for Conversion Use Case.
 */
public class SelectForConversionController {

    private final SelectForConversionInputBoundary selectForConversionUseCaseInteractor;

    public SelectForConversionController(SelectForConversionInputBoundary selectForConversionUseCaseInteractor) {
        this.selectForConversionUseCaseInteractor = selectForConversionUseCaseInteractor;
    }

    /**
     * Executes the Select for Conversion Use Case.
     * @param downloadTasks the Map of url to chosen format
     */
    public void execute(List<VideoData> downloadTasks) {
        final SelectForConversionInputData selectForConversionInputData = new SelectForConversionInputData(downloadTasks);
        selectForConversionUseCaseInteractor.execute(selectForConversionInputData);
    }

    /**
     * Executes the "switch to URLView" Use Case.
     */
    public void switchToUrlView() {
        selectForConversionUseCaseInteractor.switchToUrlView();
    }
}

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
     * @param convertTasks the List of VideoData
     */
    public void execute(List<VideoData> convertTasks, String inputFolder) {
        final SelectForConversionInputData selectForConversionInputData =
                new SelectForConversionInputData(convertTasks, inputFolder);
        selectForConversionUseCaseInteractor.execute(selectForConversionInputData);
    }

    /**
     * Executes the "switch to MenuView" Use Case.
     */
    public void switchToUrlView() {
        selectForConversionUseCaseInteractor.switchToUrlView();
    }
}

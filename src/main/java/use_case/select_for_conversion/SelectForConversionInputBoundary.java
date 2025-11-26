package use_case.select_for_conversion;

/**
 * The input boundary for the Select for Conversion Use Case.
 */
public interface SelectForConversionInputBoundary {

    /**
     * Execute the Select for Conversion Use Case.
     * @param selectForConversionInputData the input data for this use case
     */
    SelectForConversionOutputData execute(SelectForConversionInputData selectForConversionInputData);

    /**
     * Execute the switch to url view use case.
     */
    void switchToUrlView();
}

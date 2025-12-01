package use_case.select_for_conversion;

/**
 * The input boundary for the Select for Conversion Use Case.
 */
public interface SelectForConversionOutputBoundary {

    /**
     * Prepares the success view for the Change Password Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(SelectForConversionOutputData outputData);

    /**
     * Prepares the failure view for the Change Password Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    void switchToUrlView();
}

package use_case.url;

/**
 *
 * Output Boundary for the URL validation use case.
 */
public interface URLOutputBoundary {
    /**
     * Prepares the success view for the URL validation use case.
     * @param outputData the output data
     */
    void prepareSuccessView(URLOutputData outputData);

    /**
     * Prepares the fail view for the URL validation use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}

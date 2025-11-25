package main.java.use_case.select_for_conversion.url;

/**
 * Input Boundary for the URL validation use case.
 */

public interface URLInputBoundary {
    /**
     * Executes the URL validation use case.
     * @param urlInputData the input data containing the URL to validate
     */
    void execute(URLInputData urlInputData);
}
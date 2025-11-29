package use_case;

/**
 * The input boundary for the Select for Conversion Use Case.
 */
public interface SelectForConversionOutputBoundary {

    /**
     * Execute the Select for Conversion Use Case.
     * @param outputData the output data for this use case
     */
    void execute(SelectForConversionOutputData outputData);

}

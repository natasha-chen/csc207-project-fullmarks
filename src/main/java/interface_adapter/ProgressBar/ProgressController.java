package interface_adapter.ProgressBar;
import use_case.progress.ProgressInputBoundary;

public class ProgressController {
    private final ProgressInputBoundary interacter;

    public ProgressController(ProgressInputBoundary interacter){
        this.interacter = interacter;
    }

    /**
     * Executes the Login Use Case.
     * @param url the url of the YouTube video
     */
    public void startDownload(String url){
        interacter.execute(url);
    }

    public void cancelDownload(){
        interacter.cancel;
    }

}

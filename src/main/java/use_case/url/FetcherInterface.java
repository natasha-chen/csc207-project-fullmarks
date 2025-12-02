package use_case.url;

import org.json.JSONObject;

public interface FetcherInterface {
    JSONObject fetchInfo(String url) throws Exception;
}

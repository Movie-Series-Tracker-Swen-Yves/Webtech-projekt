package htw.webtech.todo.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OmdbSearchResponse {
    @JsonProperty("Search")
    private OmdbSearchItem[] search;

    @JsonProperty("totalResults")
    private String totalResults;

    @JsonProperty("Response")
    private String response;

    @JsonProperty("Error")
    private String error;

    public OmdbSearchItem[] getSearch() { return search; }
    public String getTotalResults() { return totalResults; }
    public String getResponse() { return response; }
    public String getError() { return error; }
}

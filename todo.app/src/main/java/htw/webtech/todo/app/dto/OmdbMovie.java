package htw.webtech.todo.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OmdbMovie {
    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private String year;

    @JsonProperty("Plot")
    private String plot;

    @JsonProperty("Poster")
    private String poster;

    @JsonProperty("imdbID")
    private String imdbID;

    @JsonProperty("Response")
    private String response; // "True"/"False"

    @JsonProperty("Error")
    private String error;

    // getters
    public String getTitle() { return title; }
    public String getYear() { return year; }
    public String getPlot() { return plot; }
    public String getPoster() { return poster; }
    public String getImdbID() { return imdbID; }
    public String getResponse() { return response; }
    public String getError() { return error; }
}

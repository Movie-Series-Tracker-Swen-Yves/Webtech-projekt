package htw.webtech.todo.app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MovieApiService {
    // TODO: lege ihn in application.properties ab (M3/M4)
    private final String apiKey = "d89dd9a2";
    private final RestClient client = RestClient.create();

    public String fetchByTitle(String title) {
        String url = "https://www.omdbapi.com/?t=" + title.replace(" ", "+") + "&apikey=" + apiKey + "&plot=full";
        return client.get().uri(url).retrieve().body(String.class);
    }
}

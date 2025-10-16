package htw.webtech.todo.app.service;

import htw.webtech.todo.app.dto.OmdbMovie;
import htw.webtech.todo.app.dto.OmdbSearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MovieApiService {

    private static final String API_KEY = "d89dd9a2";
    private final RestClient http = RestClient.create("https://www.omdbapi.com");

    public OmdbMovie fetchById(String imdbId) {
        if (imdbId == null || imdbId.isBlank()) return null;
        return http.get()
                .uri(uri -> uri.queryParam("apikey", API_KEY)
                        .queryParam("i", imdbId)
                        .build())
                .retrieve()
                .body(OmdbMovie.class);
    }

    public OmdbMovie fetchByTitle(String title, String type) {
        if (title == null || title.isBlank()) return null;
        var req = http.get().uri(uri -> {
            var b = uri.queryParam("apikey", API_KEY)
                    .queryParam("t", title);
            if (type != null && !type.isBlank()) {
                b.queryParam("type", type);
            }
            return b.build();
        });
        return req.retrieve().body(OmdbMovie.class);
    }

    public OmdbSearchResponse search(String q, String type) {
        if (q == null || q.isBlank()) return null;
        return http.get()
                .uri(uri -> {
                    var b = uri.queryParam("apikey", API_KEY)
                            .queryParam("s", q);
                    if (type != null && !type.isBlank()) b.queryParam("type", type);
                    return b.build();
                })
                .retrieve()
                .body(OmdbSearchResponse.class);
    }
}

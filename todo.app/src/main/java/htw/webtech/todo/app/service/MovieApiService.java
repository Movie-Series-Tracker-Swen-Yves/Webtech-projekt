package htw.webtech.todo.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class MovieApiService {

    private final RestClient client = RestClient.create();

    // lädt aus application.properties oder aus ENV (Render)
    public MovieApiService(
            @Value("${omdb.api.key:${OMDB_API_KEY:}}") String apiKey) {
        this.apiKey = apiKey;
    }

    private final String apiKey;

    public String fetchByTitle(String title) {
        if (apiKey == null || apiKey.isBlank()) {
            // Eigene, klare Fehlermeldung statt Whitelabel
            throw new MissingApiKeyException("OMDb API-Key fehlt. Setze 'omdb.api.key' oder ENV 'OMDB_API_KEY'.");
        }
        String url = "https://www.omdbapi.com/?t=" + title.replace(" ", "+")
                + "&apikey=" + apiKey + "&plot=full";

        try {
            return client.get().uri(url).retrieve().body(String.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                // Typischer Fall: falscher/fehlender Key → kurze Antwort für Frontend
                return """
          {"Response":"False","Error":"Unauthorized (API key invalid or missing)"}""";
            }
            throw e; // andere Fehler weiterwerfen → globaler Handler fängt das
        }
    }

    // kleine eigene Runtime-Exception
    public static class MissingApiKeyException extends RuntimeException {
        public MissingApiKeyException(String msg) { super(msg); }
    }
}

package htw.webtech.todo.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@RestController
public class MovieApiController {

    private static final String API_KEY = "d89dd9a2";
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/api/movie")
    public ResponseEntity<String> getMovieInfo(@RequestParam String title) {
        String url = "https://www.omdbapi.com/?t="
                + title.replace(" ", "+")
                + "&apikey=" + API_KEY;
        String response = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(response);
    }
}

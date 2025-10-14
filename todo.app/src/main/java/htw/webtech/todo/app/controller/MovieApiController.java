package htw.webtech.todo.app.controller;

import htw.webtech.todo.app.service.MovieApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movieinfo")
public class MovieApiController {

    private final MovieApiService service;

    public MovieApiController(MovieApiService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<String> byTitle(@RequestParam String title) {
        try {
            return ResponseEntity.ok(service.fetchByTitle(title));
        } catch (MovieApiService.MissingApiKeyException ex) {
            return ResponseEntity.status(500).body("""
        {"Response":"False","Error":"Server misconfigured: OMDb API key missing"}""");
        }
    }
}

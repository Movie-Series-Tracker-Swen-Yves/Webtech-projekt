package htw.webtech.todo.app.controller;

import htw.webtech.todo.app.service.MovieApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movieinfo")
public class MovieApiController {

    private final MovieApiService service;
    public MovieApiController(MovieApiService service) { this.service = service; }

    // Beispiel: /api/movieinfo?title=Inception
    @GetMapping
    public ResponseEntity<String> byTitle(@RequestParam String title) {
        return ResponseEntity.ok(service.fetchByTitle(title));
    }
}

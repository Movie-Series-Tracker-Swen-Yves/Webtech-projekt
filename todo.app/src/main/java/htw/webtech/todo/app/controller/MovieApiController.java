package htw.webtech.todo.app.controller;

import htw.webtech.todo.app.service.MovieApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/omdb")
public class MovieApiController {

    private final MovieApiService svc;

    public MovieApiController(MovieApiService svc) {
        this.svc = svc;
    }

    // GET /api/omdb?t=Star%20Wars&type=movie  ODER  /api/omdb?i=tt0076759
    @GetMapping
    public ResponseEntity<?> get(@RequestParam(required = false, name = "t") String title,
                                 @RequestParam(required = false, name = "i") String imdbId,
                                 @RequestParam(required = false, name = "type") String type) {
        if (imdbId != null && !imdbId.isBlank()) {
            var m = svc.fetchById(imdbId);
            return (m != null && "True".equalsIgnoreCase(m.getResponse()))
                    ? ResponseEntity.ok(m) : ResponseEntity.notFound().build();
        }
        if (title != null && !title.isBlank()) {
            var m = svc.fetchByTitle(title, type);
            return (m != null && m.getTitle() != null)
                    ? ResponseEntity.ok(m) : ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().body("Use ?t=TITLE or ?i=IMDBID");
    }

    // GET /api/omdb/search?q=Star%20Wars&type=movie
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam("q") String q,
                                    @RequestParam(required = false, name = "type") String type) {
        var res = svc.search(q, type);
        return ResponseEntity.ok(res);
    }
}

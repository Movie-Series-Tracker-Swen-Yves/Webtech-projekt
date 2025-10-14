package htw.webtech.todo.app.controller;

import htw.webtech.todo.app.dto.CreateFilmDto;
import htw.webtech.todo.app.model.Film;
import htw.webtech.todo.app.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    private final FilmService service;
    public FilmController(FilmService service) { this.service = service; }

    @GetMapping public List<Film> all() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Film> byId(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Film> create(@RequestBody CreateFilmDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Film> update(@PathVariable Long id,
                                       @RequestParam(required=false) Integer minute,
                                       @RequestParam(required=false) String notes,
                                       @RequestParam(required=false) String title) {
        return service.updatePartial(id, minute, notes, title)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

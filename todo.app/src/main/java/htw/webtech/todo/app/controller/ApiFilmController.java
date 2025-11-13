package htw.webtech.todo.app.controller;

import htw.webtech.todo.app.dto.CreateFilmDto;
import htw.webtech.todo.app.dto.FilmDto;
import htw.webtech.todo.app.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/films")
public class ApiFilmController {
    private final FilmService service;

    public ApiFilmController(FilmService service) { this.service = service; }

    @GetMapping public List<FilmDto> list() { return service.findAll(); }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<FilmDto> create(@RequestBody CreateFilmDto dto) {
        var created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/films/" + created.getId())).body(created);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CreateFilmDto partial) {
        service.updatePartial(id, partial.getMinutes(), partial.getNotes(), partial.getTitle());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

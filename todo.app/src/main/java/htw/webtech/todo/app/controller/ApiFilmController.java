package htw.webtech.todo.app.controller;

import htw.webtech.todo.app.dto.CreateFilmDto;
import htw.webtech.todo.app.dto.FilmDto;
import htw.webtech.todo.app.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/films")
public class ApiFilmController {

    private final FilmService service;

    public ApiFilmController(FilmService service) {
        this.service = service;
    }

    // --- GET /api/films ------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<FilmDto>> list() {
        try {
            List<FilmDto> all = service.findAll();
            return ResponseEntity.ok(all);
        } catch (Exception e) {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    // --- GET /api/films/{id} -------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<FilmDto> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // --- POST /api/films -----------------------------------------------------
    // REST-POST, der FilmService.create(...) aufruft und in die DB schreibt.
    @PostMapping
    public ResponseEntity<FilmDto> create(@RequestBody CreateFilmDto dto) {
        FilmDto created = service.create(dto);
        return ResponseEntity
                .created(URI.create("/api/films/" + created.getId()))
                .body(created);
    }

    // --- PATCH /api/films/{id} ----------------------------------------------
    @PatchMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody CreateFilmDto partial) {
        service.updatePartial(
                id,
                partial.getMinutes(),
                partial.getNotes(),
                partial.getTitle()
        );
        return ResponseEntity.noContent().build();
    }

    // --- DELETE /api/films/{id} ---------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
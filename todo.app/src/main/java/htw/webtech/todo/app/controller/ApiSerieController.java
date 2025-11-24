package htw.webtech.todo.app.controller;

import htw.webtech.todo.app.dto.CreateSerieDto;
import htw.webtech.todo.app.dto.SerieDto;
import htw.webtech.todo.app.service.SerieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/series")
public class ApiSerieController {

    private final SerieService service;

    public ApiSerieController(SerieService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<SerieDto>> list() {
        try {
            return ResponseEntity.ok(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SerieDto> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SerieDto> create(@RequestBody CreateSerieDto dto) {
        SerieDto created = service.create(dto);
        return ResponseEntity
                .created(URI.create("/api/series/" + created.getId()))
                .body(created);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody CreateSerieDto partial) {
        service.updatePartial(
                id,
                partial.getSeason(),
                partial.getEpisode(),
                partial.getMinutes(),
                partial.getNotes(),
                partial.getTitle()
        );
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
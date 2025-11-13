package htw.webtech.todo.app.controller;

import htw.webtech.todo.app.dto.CreateSerieDto;
import htw.webtech.todo.app.dto.SerieDto;
import htw.webtech.todo.app.service.SerieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/series")
public class ApiSerieController {
    private final SerieService service;

    public ApiSerieController(SerieService service) { this.service = service; }

    @GetMapping public List<SerieDto> list() { return service.findAll(); }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<SerieDto> create(@RequestBody CreateSerieDto dto) {
        var created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/series/" + created.getId())).body(created);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CreateSerieDto partial) {
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

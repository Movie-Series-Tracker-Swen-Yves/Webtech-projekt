package htw.webtech.todo.app.controller;

import htw.webtech.todo.app.dto.CreateSerieDto;
import htw.webtech.todo.app.model.Serie;
import htw.webtech.todo.app.service.SerieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
public class SerieController {

    private final SerieService service;
    public SerieController(SerieService service) { this.service = service; }

    @GetMapping public List<Serie> all() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Serie> byId(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Serie> create(@RequestBody CreateSerieDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Serie> update(@PathVariable Long id,
                                        @RequestParam(required=false) Integer season,
                                        @RequestParam(required=false) Integer episode,
                                        @RequestParam(required=false) Integer minute,
                                        @RequestParam(required=false) String notes,
                                        @RequestParam(required=false) String title) {
        return service.updatePartial(id, season, episode, minute, notes, title)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

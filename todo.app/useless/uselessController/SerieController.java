package htw.webtech.todo.app.uselessController;

import htw.webtech.todo.app.dto.CreateSerieDto;
import htw.webtech.todo.app.service.SerieService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SerieController {

    private final SerieService service;

    public SerieController(SerieService service) {
        this.service = service;
    }

    // Formular-POST (x-www-form-urlencoded)
    @PostMapping("/serie")
    public String create(@ModelAttribute CreateSerieDto dto) {
        service.create(dto);
        return "redirect:/";
    }

    @PatchMapping("/serie/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestParam(required = false) Integer season,
                                       @RequestParam(required = false) Integer episode,
                                       @RequestParam(required = false) Integer minute,
                                       @RequestParam(required = false) String notes,
                                       @RequestParam(required = false) String title) {
        service.updatePartial(id, season, episode, minute, notes, title);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/serie/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

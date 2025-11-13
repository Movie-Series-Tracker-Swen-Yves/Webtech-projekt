package htw.webtech.todo.app.controller;

import htw.webtech.todo.app.dto.CreateFilmDto;
import htw.webtech.todo.app.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FilmController {

    private final FilmService service;

    public FilmController(FilmService service) {
        this.service = service;
    }

    // Formular-POST (x-www-form-urlencoded)
    @PostMapping("/film")
    public String create(@ModelAttribute CreateFilmDto dto) {
        service.create(dto);
        return "redirect:/";
    }

    // Patch via Query-Params (optional – bleibt wie gehabt)
    @PatchMapping("/film/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestParam(required = false) Integer minute,
                                       @RequestParam(required = false) String notes,
                                       @RequestParam(required = false) String title) {
        service.updatePartial(id, minute, notes, title);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/film/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

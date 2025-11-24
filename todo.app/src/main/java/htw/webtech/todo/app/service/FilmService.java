package htw.webtech.todo.app.service;

import htw.webtech.todo.app.dto.CreateFilmDto;
import htw.webtech.todo.app.dto.FilmDto;
import htw.webtech.todo.app.model.Film;
import htw.webtech.todo.app.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    private final FilmRepository repo;

    public FilmService(FilmRepository repo) {
        this.repo = repo;
    }

    public List<FilmDto> findAll() {
        return repo.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public FilmDto findById(Long id) {
        return repo.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Film " + id + " not found"));
    }

    public FilmDto create(CreateFilmDto dto) {
        Film entity = new Film();
        entity.setTitle(dto.getTitle());
        entity.setMinute(dto.getMinutes()); // DTO plural -> Entity singular
        entity.setNotes(dto.getNotes());

        Film saved = repo.save(entity);
        return toDto(saved);
    }

    /**
     * Teil-Update: nur Felder überschreiben, die nicht null sind.
     */
    public void updatePartial(Long id, Integer minutes, String notes, String title) {
        Film entity = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Film " + id + " not found"));

        if (minutes != null) {
            entity.setMinute(minutes);
        }
        if (notes != null) {
            entity.setNotes(notes);
        }
        if (title != null) {
            entity.setTitle(title);
        }

        repo.save(entity);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Film " + id + " not found");
        }
        repo.deleteById(id);
    }

    private FilmDto toDto(Film f) {
        return new FilmDto(
                f.getId(),
                f.getTitle(),
                f.getMinute(), // Entity (singular) -> DTO (plural)
                f.getNotes()
        );
    }
}
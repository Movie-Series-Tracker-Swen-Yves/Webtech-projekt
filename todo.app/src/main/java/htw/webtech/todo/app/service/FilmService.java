package htw.webtech.todo.app.service;

import htw.webtech.todo.app.dto.CreateFilmDto;
import htw.webtech.todo.app.model.Film;
import htw.webtech.todo.app.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilmService {
    private final FilmRepository repo;

    public FilmService(FilmRepository repo) {
        this.repo = repo;
    }

    public List<Film> findAll() {
        return repo.findAll();
    }

    public Film findById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    public Film create(CreateFilmDto dto) {
        var f = new Film();
        f.setTitle(dto.getTitle());
        f.setMinutes(dto.getMinutes());
        f.setNotes(dto.getNotes());
        return repo.save(f);
    }

    @Transactional
    public void updatePartial(Long id, Integer minute, String notes, String title) {
        var f = findById(id);
        if (minute != null) f.setMinutes(minute);
        if (notes != null)  f.setNotes(notes);
        if (title != null)  f.setTitle(title);
        repo.save(f);
    }

    @Transactional
    public void setImdbId(Long id, String imdbId) {
        var f = findById(id);
        f.setImdbId(imdbId);
        repo.save(f);
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }
}

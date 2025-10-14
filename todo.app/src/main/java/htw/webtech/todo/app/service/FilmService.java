package htw.webtech.todo.app.service;

import htw.webtech.todo.app.model.Film;
import htw.webtech.todo.app.dto.CreateFilmDto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class FilmService {
    private final Map<Long, Film> store = new LinkedHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public FilmService() {
        // Demo-Daten
        create(new CreateFilmDto("Inception", 45, "Spannend bis zur Hälfte"));
        create(new CreateFilmDto("Interstellar", 0, "Noch nicht angefangen"));
    }

    public List<Film> findAll() { return new ArrayList<>(store.values()); }

    public Optional<Film> findById(Long id) { return Optional.ofNullable(store.get(id)); }

    public Film create(CreateFilmDto dto) {
        Long id = seq.getAndIncrement();
        Film f = new Film(id, dto.title(), dto.minute(), dto.notes());
        store.put(id, f);
        return f;
    }

    public Optional<Film> updatePartial(Long id, Integer minute, String notes, String title) {
        Film f = store.get(id);
        if (f == null) return Optional.empty();
        if (minute != null) f.setMinute(minute);
        if (notes != null)  f.setNotes(notes);
        if (title != null)  f.setTitle(title);
        return Optional.of(f);
    }

    public boolean delete(Long id) { return store.remove(id) != null; }
}

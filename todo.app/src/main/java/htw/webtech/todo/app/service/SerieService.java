package htw.webtech.todo.app.service;

import htw.webtech.todo.app.model.Serie;
import htw.webtech.todo.app.dto.CreateSerieDto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SerieService {
    private final Map<Long, Serie> store = new LinkedHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public SerieService() {
        // Demo-Daten
        create(new CreateSerieDto("Breaking Bad", 2, 5, 0, "letzte Folge offen"));
        create(new CreateSerieDto("Attack on Titan", 4, 23, 10, "Finale schauen"));
    }

    public List<Serie> findAll() { return new ArrayList<>(store.values()); }

    public Optional<Serie> findById(Long id) { return Optional.ofNullable(store.get(id)); }

    public Serie create(CreateSerieDto dto) {
        Long id = seq.getAndIncrement();
        Serie s = new Serie(id, dto.title(), dto.season(), dto.episode(), dto.minute(), dto.notes());
        store.put(id, s);
        return s;
    }

    public Optional<Serie> updatePartial(Long id, Integer season, Integer episode, Integer minute, String notes, String title) {
        Serie s = store.get(id);
        if (s == null) return Optional.empty();
        if (season  != null) s.setSeason(season);
        if (episode != null) s.setEpisode(episode);
        if (minute  != null) s.setMinute(minute);
        if (notes   != null) s.setNotes(notes);
        if (title   != null) s.setTitle(title);
        return Optional.of(s);
    }

    public boolean delete(Long id) { return store.remove(id) != null; }
}

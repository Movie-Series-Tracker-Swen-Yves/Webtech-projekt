package htw.webtech.todo.app.service;

import htw.webtech.todo.app.dto.CreateSerieDto;
import htw.webtech.todo.app.dto.SerieDto;
import htw.webtech.todo.app.model.Serie;
import htw.webtech.todo.app.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {

    private final SerieRepository repo;

    public SerieService(SerieRepository repo) {
        this.repo = repo;
    }

    public List<SerieDto> findAll() {
        return repo.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public SerieDto findById(Long id) {
        return repo.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Serie " + id + " not found"));
    }

    public SerieDto create(CreateSerieDto dto) {
        Serie entity = new Serie();
        entity.setTitle(dto.getTitle());
        entity.setSeason(dto.getSeason());
        entity.setEpisode(dto.getEpisode());
        entity.setMinute(dto.getMinutes());
        entity.setNotes(dto.getNotes());

        Serie saved = repo.save(entity);
        return toDto(saved);
    }

    public void updatePartial(Long id,
                              Integer season,
                              Integer episode,
                              Integer minutes,
                              String notes,
                              String title) {

        Serie entity = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serie " + id + " not found"));

        if (season != null) entity.setSeason(season);
        if (episode != null) entity.setEpisode(episode);
        if (minutes != null) entity.setMinute(minutes);
        if (notes != null) entity.setNotes(notes);
        if (title != null) entity.setTitle(title);

        repo.save(entity);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Serie " + id + " not found");
        }
        repo.deleteById(id);
    }

    private SerieDto toDto(Serie s) {
        return new SerieDto(
                s.getId(),
                s.getTitle(),
                s.getSeason(),
                s.getEpisode(),
                s.getMinute(), // Entity singular -> DTO plural
                s.getNotes()
        );
    }
}
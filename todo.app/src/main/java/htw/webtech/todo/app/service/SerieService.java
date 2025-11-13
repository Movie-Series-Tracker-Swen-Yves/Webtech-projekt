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

    public SerieService(SerieRepository repo) { this.repo = repo; }

    public List<SerieDto> findAll() {
        return repo.findAll().stream().map(this::toDto).toList();
    }

    public SerieDto create(CreateSerieDto dto) {
        Serie s = new Serie();
        s.setTitle(dto.getTitle());
        s.setSeason(dto.getSeason());
        s.setEpisode(dto.getEpisode());
        if (dto.getMinutes() != null) s.setMinute(dto.getMinutes());
        s.setNotes(dto.getNotes());
        return toDto(repo.save(s));
    }

    public void updatePartial(Long id, Integer season, Integer episode, Integer minutes, String notes, String title) {
        Serie s = repo.findById(id).orElseThrow();
        if (season != null)  s.setSeason(season);
        if (episode != null) s.setEpisode(episode);
        if (minutes != null) s.setMinute(minutes);
        if (notes != null)   s.setNotes(notes);
        if (title != null)   s.setTitle(title);
        repo.save(s);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Serie " + id + " not found");
        repo.deleteById(id);
    }

    private SerieDto toDto(Serie s) {
        return new SerieDto(
                s.getId(),
                s.getTitle(),
                s.getSeason(),
                s.getEpisode(),
                s.getMinute(), // Entity (singular) -> DTO (plural)
                s.getNotes()
        );
    }
}

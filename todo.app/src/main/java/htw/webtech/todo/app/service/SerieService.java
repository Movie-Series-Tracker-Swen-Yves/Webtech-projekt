package htw.webtech.todo.app.service;

import htw.webtech.todo.app.dto.CreateSerieDto;
import htw.webtech.todo.app.model.Serie;
import htw.webtech.todo.app.repository.SerieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SerieService {
    private final SerieRepository repo;

    public SerieService(SerieRepository repo) {
        this.repo = repo;
    }

    public List<Serie> findAll() {
        return repo.findAll();
    }

    public Serie findById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    public Serie create(CreateSerieDto dto) {
        var s = new Serie();
        s.setTitle(dto.getTitle());
        s.setSeason(dto.getSeason());
        s.setEpisode(dto.getEpisode());
        s.setMinutes(dto.getMinutes());
        s.setNotes(dto.getNotes());
        return repo.save(s);
    }

    @Transactional
    public void updatePartial(Long id, Integer season, Integer episode,
                              Integer minute, String notes, String title) {
        var s = findById(id);
        if (season != null)  s.setSeason(season);
        if (episode != null) s.setEpisode(episode);
        if (minute != null)  s.setMinutes(minute);
        if (notes != null)   s.setNotes(notes);
        if (title != null)   s.setTitle(title);
        repo.save(s);
    }

    @Transactional
    public void setImdbId(Long id, String imdbId) {
        var s = findById(id);
        s.setImdbId(imdbId);
        repo.save(s);
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }
}

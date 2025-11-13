package htw.webtech.todo.app.uselessController;

import htw.webtech.todo.app.service.FilmService;
import htw.webtech.todo.app.service.MovieApiService;
import htw.webtech.todo.app.service.SerieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

    private final FilmService filmService;
    private final SerieService serieService;
    private final MovieApiService movieApi;

    public PageController(FilmService filmService, SerieService serieService, MovieApiService movieApi) {
        this.filmService = filmService;
        this.serieService = serieService;
        this.movieApi = movieApi;
    }

    @GetMapping("/")
    public String home(Model m) {
        m.addAttribute("filme", filmService.findAll());
        m.addAttribute("serien", serieService.findAll());
        return "index";
    }

    // Film-Details (HTML)
    @GetMapping("/film/{id}")
    public String film(@PathVariable Long id, Model m) {
        var f = filmService.findById(id);
        m.addAttribute("film", f);

        // wenn IMDb-ID schon verknüpft, hole exakt; sonst suche nach Titel
        if (f.getImdbId() != null && !f.getImdbId().isBlank()) {
            m.addAttribute("omdb", movieApi.fetchById(f.getImdbId()));
            return "details-film";
        } else {
            var omdb = movieApi.fetchByTitle(f.getTitle(), "movie");
            if (omdb != null && omdb.getTitle() != null) {
                m.addAttribute("omdb", omdb);
            } else {
                m.addAttribute("search", movieApi.search(f.getTitle(), "movie"));
            }
            return "details-film";
        }
    }

    // Film mit ausgewählter IMDb-ID verlinken (kommt vom Details-Template)
    @PostMapping("/film/{id}/link")
    public String linkFilm(@PathVariable Long id, @RequestParam("imdb") String imdbId) {
        filmService.setImdbId(id, imdbId);
        return "redirect:/film/" + id;
    }

    // Serie-Details (HTML)
    @GetMapping("/serie/{id}")
    public String serie(@PathVariable Long id, Model m) {
        var s = serieService.findById(id);
        m.addAttribute("serie", s);

        if (s.getImdbId() != null && !s.getImdbId().isBlank()) {
            m.addAttribute("omdb", movieApi.fetchById(s.getImdbId()));
            return "details-serie";
        } else {
            var omdb = movieApi.fetchByTitle(s.getTitle(), "series");
            if (omdb != null && omdb.getTitle() != null) {
                m.addAttribute("omdb", omdb);
            } else {
                m.addAttribute("search", movieApi.search(s.getTitle(), "series"));
            }
            return "details-serie";
        }
    }

    @PostMapping("/serie/{id}/link")
    public String linkSerie(@PathVariable Long id, @RequestParam("imdb") String imdbId) {
        serieService.setImdbId(id, imdbId);
        return "redirect:/serie/" + id;
    }
}

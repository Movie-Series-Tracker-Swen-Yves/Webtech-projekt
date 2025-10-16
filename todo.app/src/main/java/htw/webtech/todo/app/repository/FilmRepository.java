package htw.webtech.todo.app.repository;

import htw.webtech.todo.app.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> { }

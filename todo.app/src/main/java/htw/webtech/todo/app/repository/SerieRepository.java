package htw.webtech.todo.app.repository;

import htw.webtech.todo.app.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long> { }

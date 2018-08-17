package tk.roydgar.cinema.model.repository;

import org.springframework.data.repository.CrudRepository;
import tk.roydgar.cinema.model.entity.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long> {
}

package tk.roydgar.cinema.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tk.roydgar.cinema.model.entity.Session;

import java.time.LocalDate;
import java.util.List;

public interface SessionRepository extends CrudRepository<Session, Long> {

    @Query(value = "SELECT * FROM session WHERE DATE(time) = ?1", nativeQuery = true)
    List<Session> findAllByDate(LocalDate date);
    
}

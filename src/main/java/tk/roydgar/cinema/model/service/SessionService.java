package tk.roydgar.cinema.model.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.roydgar.cinema.model.entity.Session;
import tk.roydgar.cinema.model.repository.SessionRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SessionService {

    private SessionRepository sessionRepository;

    public List<Session> findByDate(LocalDate localDate) {
        return sessionRepository.findAllByDate(localDate);
    }

}

package tk.roydgar.cinema.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.roydgar.cinema.model.entity.Session;
import tk.roydgar.cinema.model.service.SessionService;
import tk.roydgar.cinema.util.constants.Mappings;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(Mappings.SESSION)
@CrossOrigin(origins = "http://localhost:63342", allowedHeaders = "*", exposedHeaders = {"error-message"})
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SessionController {

    private SessionService sessionService;

    @GetMapping
    public List<Session> getSession(@RequestParam("date") String date) {
        return sessionService.findByDate(LocalDate.parse(date));
    }

}

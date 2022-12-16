package al.crystal.conferenceApp.controller;

import al.crystal.conferenceApp.dto.SessionDTO;
import al.crystal.conferenceApp.model.Session;
import al.crystal.conferenceApp.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public String addSession(@RequestBody SessionDTO session) {
        return sessionService.createSession(session);
    }

    @GetMapping("/{id}")
    public Session getSession(@PathVariable Long id) {
        return sessionService.getSession(id);
    }


    @GetMapping()
    public List<Session> getSessions(@RequestParam(required = false) String date,
                                     @RequestParam(required = false) String location) {
        return this.sessionService.getSessions(date, location);
    }

    @GetMapping("/dates")
    public List<String> getSessionsDates(@RequestParam(required = false) String location) {
        return this.sessionService.getSessionsDates(location);
    }

    @GetMapping("/locations")
    public List<String> getLocations(@RequestParam(required = false) String date) {
        return this.sessionService.getSessionsLocations(date);
    }


}

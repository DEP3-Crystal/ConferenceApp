package al.crystal.conferenceApp.controller;

import al.crystal.conferenceApp.dto.SessionDTO;
import al.crystal.conferenceApp.model.Session;
import al.crystal.conferenceApp.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/add")
    public String addSession(@RequestBody SessionDTO session) {
        return sessionService.createSession(session);
    }

    @GetMapping("/{id}")
    public Session getSession(@PathVariable Long id) {
        return sessionService.getSession(id);
    }
}

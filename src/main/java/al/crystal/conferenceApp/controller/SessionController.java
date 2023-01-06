package al.crystal.conferenceApp.controller;

import al.crystal.conferenceApp.dto.SessionDTO;
import al.crystal.conferenceApp.model.Session;
import al.crystal.conferenceApp.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public Session addSession(@RequestBody SessionDTO session) {
        return sessionService.createSession(session);
    }

    @GetMapping("/{id}")
    public SessionDTO getSession(@PathVariable Long id) {
        return sessionService.getOneSession(id);
    }

//    @GetMapping("event/{id}")
//    public  List<SessionDTO> getSessionsByEvent(@PathVariable Long id){
//        return sessionService.getSessionsByEvent(id);
//    }


    @GetMapping()
    public List<SessionDTO> getSessions(@RequestParam(required = false) String date,
                                        @RequestParam(required = false) String location,
                                        @RequestParam(required = false) Long eventId) {
        return this.sessionService.getSessions(date, location, eventId);
    }

    @GetMapping("/")
    public List<SessionDTO> getSessions() {
        return sessionService.getAllSessionsDTO();
    }


    @GetMapping("/dates")
    public List<String> getSessionsDates(@RequestParam(required = false) String location,
                                         @RequestParam(required = false) Long eventId) {
        List<String> ls = this.sessionService.getSessionsDates(location, eventId);
        return ls;
    }

    @GetMapping("/locations")
    public List<String> getLocations(@RequestParam(required = false) String date,
                                     @RequestParam(required = false) Long eventId) {
        List<String> ls = this.sessionService.getSessionsLocations(date, eventId);
        return ls;
    }
}

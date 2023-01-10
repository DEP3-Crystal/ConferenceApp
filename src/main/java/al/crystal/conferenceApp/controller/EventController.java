package al.crystal.conferenceApp.controller;

import al.crystal.conferenceApp.dto.EventDTO;
import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public List<EventDTO> getAllEvents() {
        return this.eventService.getAllEvents();
    }

    @GetMapping(value = "/organiser/{id}")
    public List<EventDTO> getAllEventsByOrganiserId(@PathVariable Long id) {
        return this.eventService.getAllEventsByOrganiserId(id);
    }

    @GetMapping(value = "/{id}")
    public Event getEventById(@PathVariable Long id) {
        return this.eventService.getEventById(id);
    }

    @GetMapping(value="/toShow")
    public List<Event> eventToShow(){
        return eventService.eventToShow();
    }

    @DeleteMapping(value = "/{id}")
    public List<Event> deleteEvent(@PathVariable Long id) {
        return this.eventService.deleteEvent(id);
    }

    @PutMapping(value = "/")
    public List<EventDTO> updateEvents(@RequestBody Event event) {
        return this.eventService.updateEvent(event);
    }

    @PostMapping(value = "/")
    public Event createEvent(@RequestBody Event event) {
        try {
            System.out.println(event);
            return eventService.saveEvent(event);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

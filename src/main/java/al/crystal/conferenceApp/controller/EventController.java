package al.crystal.conferenceApp.controller;

import al.crystal.conferenceApp.dto.EventDTO;
import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.validator.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping(value = "/")
    public List<Event> getAllEvents() {
        return this.eventService.getAllEvents();
    }

    @GetMapping(value = "/{id}")
    public Event getEventById(@PathVariable Long id) {
        return this.eventService.getEventById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public List<Event> deleteEvent(@PathVariable Long id) {
        return this.eventService.deleteEvent(id);
    }

    @PutMapping(value = "/update")
    public List<Event> updateEvents(@RequestBody Event event) {
        return this.eventService.updateEvent(event);
    }

    @PostMapping("/add")
    public Event createEvent(@RequestBody EventDTO eventDTO) {
        return eventService.saveEvent(eventDTO);

    }


}

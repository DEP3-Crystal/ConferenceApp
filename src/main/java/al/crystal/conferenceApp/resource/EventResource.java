package al.crystal.conferenceApp.resource;

import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/events")
public class EventResource {
  @Autowired
  EventService eventService;

  @PostMapping(value="/save")
  public String saveEvent(@Validated @RequestBody Event event) {
  return  this.eventService.saveEvent(event);
  }
  @GetMapping(value="/")
  public List<Event> getAllEvents() {
    return this.eventService.getAllEvents();
  }

  @GetMapping(value="/{id}")
  public Event getEventById(@PathVariable UUID id) {
    return this.eventService.getEventById(id);
  }
  @DeleteMapping(value="/delete/{id}")
  public List<Event> deleteEvent(@PathVariable UUID id){
    return this.eventService.deleteEvent(id);
  }

  @PutMapping(value="/update")
  public List<Event> updateEvents(@RequestBody Event event){
     return this.eventService.updateEvent(event);

  }



}

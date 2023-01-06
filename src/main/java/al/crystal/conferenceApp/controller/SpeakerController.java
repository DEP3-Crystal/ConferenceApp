package al.crystal.conferenceApp.controller;

import al.crystal.conferenceApp.dto.speaker.SpeakerDTO;
import al.crystal.conferenceApp.model.Speaker;
import al.crystal.conferenceApp.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/speaker")
public class SpeakerController {

    @Autowired
    private SpeakerService speakerService;

    @PostMapping("/add")
    public String createSpeaker(@RequestBody Speaker speaker) {
        return speakerService.saveSpeaker(speaker);
    }

    @GetMapping("/all")
    public List<SpeakerDTO> getEventSpeakers(@RequestParam(required = true) Long eventId) {
        return speakerService.getAllSpeakersByEvent(eventId);
    }

}

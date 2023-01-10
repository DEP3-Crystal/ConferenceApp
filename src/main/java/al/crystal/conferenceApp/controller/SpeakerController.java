package al.crystal.conferenceApp.controller;

import al.crystal.conferenceApp.dto.speaker.SpeakerDTO;
import al.crystal.conferenceApp.model.Speaker;
import al.crystal.conferenceApp.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/speaker")
public class SpeakerController {

    @Autowired
    private SpeakerService speakerService;

    @PostMapping("")
    public List<SpeakerDTO> createSpeaker(@RequestBody Speaker speaker) {
        return (speakerService.saveSpeaker(speaker));
    }

    @GetMapping("/{eventId}")
    public List<SpeakerDTO> getEventSpeakers(@RequestParam(required = true) Long eventId) {
        return speakerService.getAllSpeakersByEvent(eventId);
    }

    @GetMapping("")
    public List<SpeakerDTO> getAllSpeaker(){
        return speakerService.getAllSpeakers();
    }

}

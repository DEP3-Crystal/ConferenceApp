package al.crystal.conferenceApp.controller;

import al.crystal.conferenceApp.dto.SpeakerRateForRatingDto;
import al.crystal.conferenceApp.dto.speaker.SpeakerDTO;
import al.crystal.conferenceApp.exception.ResourceAlreadyExistsException;
import al.crystal.conferenceApp.exception.ResourceNotFoundException;
import al.crystal.conferenceApp.mapper.SpeakerRateSecondMapper;
import al.crystal.conferenceApp.model.Speaker;
import al.crystal.conferenceApp.model.SpeakerRate;
import al.crystal.conferenceApp.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/speaker")
public class SpeakerController {

    @Autowired
    private SpeakerService speakerService;

    @PostMapping("/add/{eventId}")
    public ResponseEntity<Boolean> createSpeaker(@RequestBody Speaker speaker, @PathVariable Long eventId) {
        boolean result = speakerService.saveSpeaker(speaker, eventId);
        if(result){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{eventId}")
    public List<SpeakerDTO> getSpeakersByEvent(@PathVariable Long eventId) {
        return speakerService.getAllSpeakersByEvent(eventId);
    }
    @GetMapping("/all")
    public List<SpeakerDTO> getEventSpeakers(@RequestParam(required = true) Long eventId) {
        return speakerService.getAllSpeakersByEvent(eventId);
    }

    @PostMapping("/rate")
    public SpeakerRateForRatingDto rateSpeaker(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        Long speakerId = Long.parseLong(body.get("speakerId"));
        int rateSpeaker = Integer.parseInt(body.get("rateSpeaker"));

        SpeakerRate speakerRateById = this.speakerService.findSpeakerRateById(email, speakerId);
        if (speakerRateById != null) {
            SpeakerRate ratedSpeaker = this.speakerService.rateSpeaker(speakerRateById, rateSpeaker);
            if (ratedSpeaker != null) {
                SpeakerRateForRatingDto speakerRateForRatingDto = SpeakerRateSecondMapper.Instance.speakerRateDto(ratedSpeaker);
                return speakerRateForRatingDto;
            } else {
                throw new ResourceAlreadyExistsException("This Speaker has already been evaluated");
            }
        } else {
            throw new ResourceNotFoundException("The resource was not found in the DB");
        }
    }

    @GetMapping("/checkrate")
    public ResponseEntity<Integer> checkRatedSession(@RequestParam(required = true) String email,
                                                     @RequestParam(required = true) Long speakerId) {
        Integer rated = this.speakerService.checkRatedSpeaker(email, speakerId);
        if (rated != null) {
            return new ResponseEntity<>(rated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
}

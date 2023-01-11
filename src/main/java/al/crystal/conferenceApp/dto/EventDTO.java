package al.crystal.conferenceApp.dto;

import al.crystal.conferenceApp.dto.speaker.SpeakerDTO;
import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.model.Organiser;
import al.crystal.conferenceApp.model.Speaker;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EventDTO {

    private Long id;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate startDay;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate endDay;
    private String location;
    private int capacity;
    private Organiser organiser;
    private String description;
    private String eventImage;

    private List<SpeakerDTO> speakerDTOS;

    public EventDTO(String title, LocalDate startDay, LocalDate endDay, String location, int capacity, Organiser organiser, String description, String eventImage) {
        this.title = title;
        this.startDay = startDay;
        this.endDay = endDay;
        this.location = location;
        this.capacity = capacity;
        this.organiser = organiser;
        this.description = description;
        this.eventImage = eventImage;

    }
}



package al.crystal.conferenceApp.dto;

import al.crystal.conferenceApp.dto.speaker.SpeakerDTO;
import al.crystal.conferenceApp.model.Organiser;
import al.crystal.conferenceApp.model.Speaker;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EventDTO {


    private  Long id;
    private String title;
    @JsonFormat(pattern="yyyy-MM-dd", shape=JsonFormat.Shape.STRING)
    private LocalDate startDay;
    @JsonFormat(pattern="yyyy-MM-dd", shape=JsonFormat.Shape.STRING)
    private LocalDate endDay;
    private String location;
    private int capacity;
    private Long organiserId;
    private List<SpeakerDTO> speakerDTOS;


}

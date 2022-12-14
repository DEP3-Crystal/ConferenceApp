package al.crystal.conferenceApp.dto;

import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.model.Speaker;
import al.crystal.conferenceApp.model.Track;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SessionDTO {

    private String title;
    private String description;
    private String type;
    private int capacity;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Track track;
    private Event event;
    private List<Speaker> speakers;

}

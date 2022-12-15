package al.crystal.conferenceApp.dto;

import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.model.Speaker;
import al.crystal.conferenceApp.model.Track;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SessionDTO {

    private String title;
    private String description;
    private String type;
    private int capacity;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime endTime;
    private Track track;
    private Event event;
    private Set<Speaker> speakers;


}

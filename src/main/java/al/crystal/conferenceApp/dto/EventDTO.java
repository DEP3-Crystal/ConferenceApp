package al.crystal.conferenceApp.dto;

import al.crystal.conferenceApp.model.Organiser;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EventDTO {

    private Long id;
    private String title;
    @JsonFormat(pattern="yyyy-MM-dd")//, shape=JsonFormat.Shape.STRING)
    private LocalDate startDay;
    @JsonFormat(pattern="yyyy-MM-dd")//, shape=JsonFormat.Shape.STRING)
    private LocalDate endDay;
    private String location;
    private int capacity;
    private Organiser organiser;
    private String description;
    private String eventImage;

}

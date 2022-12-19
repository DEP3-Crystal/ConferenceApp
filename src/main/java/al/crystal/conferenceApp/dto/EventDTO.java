package al.crystal.conferenceApp.dto;

import al.crystal.conferenceApp.model.Organiser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventDTO {

    private String title;
    private LocalDate startDay;
    private LocalDate endDay;
    private String location;
    private int capacity;
    private Organiser organiser;
    private String eventImage;
    private String description;

}

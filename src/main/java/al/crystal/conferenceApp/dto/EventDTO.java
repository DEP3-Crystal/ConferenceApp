package al.crystal.conferenceApp.dto;

import al.crystal.conferenceApp.model.Organiser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventDTO {

    private String title;
    private Date startDay;
    private Date endDay;
    private String location;
    private boolean status;
    private int capacity;
    private Organiser organiser;


}

package al.crystal.conferenceApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "events")
public class Event {
    @Id
    private UUID id;
    private String title;
    private Date startDay;
    private Date endDay;
    private String location;
    //Status Open or Restricted
    private boolean participation;
    //New, Ongoing, Ended
    private int eventStatus;
    private int capacity;
    @ManyToOne
    @JoinColumn(name = "organiser_id", nullable = false)
    private User organiser;

    @OneToMany(mappedBy = "event")
    private List<Track> tracks;

    @ManyToMany
    @JoinTable(name = "participant_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    List<User> participants;

    private String eventImage;
    private String summary;
    private String description;

}

package al.crystal.conferenceApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "events")
@Builder
public class Event {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String title;
    private Date startDay;
    private Date endDay;
    private String location;
    //Status Open or Restricted
    private boolean participation;
    //New, Ongoing, Ended
    private int eventStatus;
    private int capacity;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Organiser organiser;

    @JsonManagedReference
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Track> tracks;

    @ManyToMany
    @JoinTable(name = "participant_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    List<User> participants;

    private String eventImage;

    private String description;

}

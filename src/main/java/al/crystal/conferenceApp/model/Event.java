package al.crystal.conferenceApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "events")
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Event {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String title;
    @JsonFormat(pattern="yyyy-MM-dd", shape=JsonFormat.Shape.STRING)
    private LocalDate startDay;
    @JsonFormat(pattern="yyyy-MM-dd", shape=JsonFormat.Shape.STRING)
    private LocalDate endDay;
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


    @ManyToMany
    @JoinTable(name = "participant_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Participant> participants;
    private String eventImage;
    private String description;

}

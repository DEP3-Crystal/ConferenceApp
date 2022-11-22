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
@Entity(name = "event")
public class Event {
    @Id
    private UUID id;
    private String title;
    private Date startDay;
    private Date endDay;
    private String location;
    private boolean status;
    private int capacity;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Organiser organiser;

    @OneToMany(mappedBy = "event")
    private List<Track> tracks;

}

package al.crystal.conferenceApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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
    @Column(name = "id", unique = true)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name ="UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String title;
    private Date startDay;
    private Date endDay;
    private String location;
    private boolean status;
    private int capacity;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Organiser organiser;

    @OneToMany(mappedBy = "event")
    private List<Track> tracks;


}

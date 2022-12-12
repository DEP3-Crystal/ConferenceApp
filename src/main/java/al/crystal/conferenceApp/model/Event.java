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
@Entity(name = "event")
@Builder
public class Event {
    @Id
    @Column(name = "id", unique = true)
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(
//            name ="UUID",
//            strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String title;
    private Date startDay;
    private Date endDay;
    private String location;
    private boolean status;
    private int capacity;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Organiser organiser;

    @JsonManagedReference
    @OneToMany(mappedBy = "event",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Track> tracks;

//    @OneToMany(mappedBy = "event1")
//    private List<Session> sessions;


}

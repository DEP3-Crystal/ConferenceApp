package al.crystal.conferenceApp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "user")
//@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "type",
//        discriminatorType = DiscriminatorType.STRING)
@Data
public class User {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    //private Type userType;


    //Added from Organiser, to use when creating an Event as an Organiser
    private String companyName;
    private String biography;
    private String linkedinUrl;
    private String tweeterUrl;
    private String facebookUrl;
    private String instagramUrl;

    @OneToMany(mappedBy = "organiser")
    List<Event> organisedEvents;


    @ManyToMany
    @JoinTable(name = "participant_event",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    List<Event> participatedEvents;



    @OneToMany(mappedBy = "user")
    private List<SpeakerRate> speakerRatings;

    @OneToMany(mappedBy = "participantSession")
    private List<ParticipantSession> participantSessionList;

}

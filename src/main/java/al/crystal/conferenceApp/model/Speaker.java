package al.crystal.conferenceApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "speaker")
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String lastName;
    private String companyName;
    private String biography;
    private String title;
    private String linkedinUrl;
    private String tweeterUrl;
    private String facebookUrl;
    private String instagramUrl;
    @ManyToMany
    @JoinTable(name = "session_speaker",
            joinColumns = @JoinColumn(name = "speaker_id"),
            inverseJoinColumns = @JoinColumn(name = "session_id")
    )
    private Set<Session> sessions;

    @OneToMany(mappedBy = "speaker")
    private List<SpeakerRate> ratings;

}

package al.crystal.conferenceApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "speaker")
@Builder
@ToString
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "speaker_id")
    private long id;
    private String firstName;
    private String lastName;
    private String companyName;
    private String biography;
    @Column(name = "speaker_title")
    private String title;
    private String linkedinUrl;
    private String tweeterUrl;
    private String facebookUrl;
    private String instagramUrl;
    private double speakerRate;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "speakers")
    private Set<Session> sessions;

    @OneToMany(mappedBy = "speaker", fetch = FetchType.EAGER)
    private List<SpeakerRate> ratings;

    public Speaker(String firstName, String lastName, String companyName, String biography, String title, String linkedinUrl, String tweeterUrl, String facebookUrl, String instagramUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.biography = biography;
        this.title = title;
        this.linkedinUrl = linkedinUrl;
        this.tweeterUrl = tweeterUrl;
        this.facebookUrl = facebookUrl;
        this.instagramUrl = instagramUrl;
    }


}

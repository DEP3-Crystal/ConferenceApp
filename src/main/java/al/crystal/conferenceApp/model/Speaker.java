package al.crystal.conferenceApp.model;

import al.crystal.conferenceApp.model.pivote.SessionSpeaker;
import al.crystal.conferenceApp.model.pivote.SpeakerRate;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "speaker")
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String companyName;
    private String biography;
    private String title;
    private String linkedinUrl;
    private String tweeterUrl;
    private String facebookUrl;
    private String instagramUrl;

    @OneToMany(mappedBy ="speaker",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
//    @JsonBackReference
    private Set<SessionSpeaker> speakerSet;

    @OneToMany(mappedBy = "speaker")
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

package al.crystal.conferenceApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "speakers")
    private Set<Session> sessions;

    @OneToMany(mappedBy = "speaker")
    private List<SpeakerRate> ratings;

    public Speaker(String name, String lastName, String companyName, String biography, String title, String linkedinUrl, String tweeterUrl, String facebookUrl, String instagramUrl) {
        this.name = name;
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

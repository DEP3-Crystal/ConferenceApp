package al.crystal.conferenceApp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "speaker")
public class Speaker {
    @Id
    private UUID id;
    private String name;
    private String lastName;
    private String Bio;
    @OneToMany(mappedBy = "speaker")
    private List<SocialMedia> socialMediaUrls;

    @ManyToMany
    Set<Session> sessions;
}

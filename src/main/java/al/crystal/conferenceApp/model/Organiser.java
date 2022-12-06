package al.crystal.conferenceApp.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity(name = "organiser")
@DiscriminatorValue(value = "O")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organiser extends User {
    private String companyName;
    private String biography;
    private String linkedinUrl;
    private String tweeterUrl;
    private String facebookUrl;
    private String instagramUrl;
    @OneToMany(mappedBy = "organiser")
    private List<Event> events;
    public Organiser(UUID id, String firstName, String lastName, String email, String password, Type userType,
                     String companyName,
                     String biography,
                     String linkedinUrl,
                     String tweeterUrl,
                     String facebookUrl,
                     String instagramUrl,
                     List<Event> events)
    {
        super(id,firstName,lastName,email,password,userType);
        this.companyName = companyName;
        this.biography = biography;
        this.linkedinUrl = linkedinUrl;
        this.tweeterUrl = tweeterUrl;
        this.facebookUrl = facebookUrl;
        this.instagramUrl = instagramUrl;
        this.events = events;

    }
}

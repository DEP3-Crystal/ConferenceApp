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
//    @JsonProperty("companyName")
    private String companyName;
//    @JsonProperty("biography")
    private String biography;
//    @JsonProperty("linkedinUrl")
    private String linkedinUrl;
//    @JsonProperty("tweeterUrl")
    private String tweeterUrl;
//    @JsonProperty("facebookUrl")
    private String facebookUrl;
//    @JsonProperty("instagramUrl")
    private String instagramUrl;
    @OneToMany(mappedBy = "organiser")
//    @JsonProperty("events")
    private List<Event> events;
//    @JsonCreator
    public Organiser(UUID id, String firstName, String lastName, String email, String password, Type userType,
//                     @JsonProperty("companyName")
                     String companyName,
//                     @JsonProperty("biography")
                     String biography,
//                     @JsonProperty("linkedinUrl")
                     String linkedinUrl,
//                     @JsonProperty("tweeterUrl")
                     String tweeterUrl,
//                     @JsonProperty("facebookUrl")
                     String facebookUrl,
//                     @JsonProperty("instagramUrl")
                     String instagramUrl,
//                     @JsonProperty("events")
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

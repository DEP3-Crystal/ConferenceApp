package al.crystal.conferenceApp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

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
}

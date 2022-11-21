package al.crystal.conferenceApp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = Type.Values.ORGANISER)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organiser extends User {

    private String companyName;
    private String biography;
}

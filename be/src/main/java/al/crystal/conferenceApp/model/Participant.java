package al.crystal.conferenceApp.model;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue(value = Type.Values.PARTICIPANT)
public class Participant extends User {
}

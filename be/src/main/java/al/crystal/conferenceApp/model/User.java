package al.crystal.conferenceApp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type",
        discriminatorType = DiscriminatorType.STRING)
@Data
public abstract class User {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    @OneToMany(mappedBy = "user")
    private Set<SocialMedia> socialMedias;
    private Type type;
}

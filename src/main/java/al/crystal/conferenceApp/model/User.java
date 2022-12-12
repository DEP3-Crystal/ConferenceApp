package al.crystal.conferenceApp.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.io.Serializable;
import java.util.UUID;

@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = Organiser.class, name = "organiser"),
                @JsonSubTypes.Type(value = Participant.class, name = "participant"),
        })
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
        ,defaultImpl = Participant.class
)
@Entity
//@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type",
        discriminatorType = DiscriminatorType.STRING)
@Data
@AllArgsConstructor
public abstract class User implements Serializable {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(
//            name ="UUID",
//            strategy = "org.hibernate.id.UUIDGenerator")
//    @Column(name = "id", updatable = false, nullable = false,columnDefinition = "BINARY(16)", unique = true)
    @Column(name = "id",unique = true)
    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Type userType;
    protected User(){}


    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}

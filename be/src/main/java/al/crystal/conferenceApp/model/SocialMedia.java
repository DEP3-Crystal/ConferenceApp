package al.crystal.conferenceApp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "soclail_media")
public class SocialMedia {
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Speaker speaker;
    @Id
    private UUID id;
    private String name;
    private String url;
}

package al.crystal.conferenceApp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipantDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}

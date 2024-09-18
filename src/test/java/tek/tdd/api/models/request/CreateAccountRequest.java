package tek.tdd.api.models.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAccountRequest {
    private String email;
    private String title;
    private String firstName;
    private String lastName;
    private String gender;
    private String maritalStatus;
    private String employmentStatus;
    private String dateOfBirth;
}

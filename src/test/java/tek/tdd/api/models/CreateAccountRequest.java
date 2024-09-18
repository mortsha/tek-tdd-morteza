package tek.tdd.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

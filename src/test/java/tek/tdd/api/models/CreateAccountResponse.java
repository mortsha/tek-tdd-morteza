package tek.tdd.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountResponse {
    private int id;
    private String email;
    private String title;
    private String firstName;
    private String lastName;
    private GenderType gender;
    private MaritalStatusType maritalStatus;
    private String employmentStatus;
    private Date[] dateOfBirth;
}

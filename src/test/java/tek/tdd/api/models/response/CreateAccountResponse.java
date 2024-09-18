package tek.tdd.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tek.tdd.api.models.enums.GenderType;
import tek.tdd.api.models.enums.MaritalStatusType;

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

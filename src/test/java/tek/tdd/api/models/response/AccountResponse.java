package tek.tdd.api.models.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import tek.tdd.api.models.enums.GenderType;
import tek.tdd.api.models.enums.MaritalStatusType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountResponse {
    private int id;
    private String email;
    private String title;
    private String firstName;
    private String lastName;
    private GenderType gender;
    private MaritalStatusType maritalStatus;
    private String employmentStatus;
    @JsonIgnore
    private int[] dateOfBirth;
}

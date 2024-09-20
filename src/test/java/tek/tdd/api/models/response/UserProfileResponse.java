package tek.tdd.api.models.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import tek.tdd.api.models.enums.AccountType;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserProfileResponse {
    private int id;
    private String fullName;
    private String username;
    private AccountType accountType;
    @JsonIgnore
    private Object authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
}

package tek.tdd.api.models.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TokenRequest {
    private String username;
    private String password;
}

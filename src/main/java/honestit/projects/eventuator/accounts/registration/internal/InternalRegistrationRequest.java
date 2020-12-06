package honestit.projects.eventuator.accounts.registration.internal;

import honestit.projects.eventuator.accounts.registration.RegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class InternalRegistrationRequest implements RegistrationRequest {

    @NotBlank
    @Email
    private String username;
    @NotBlank
    @Min(8) @Max(12)
    private String password;
    @NotBlank
    @Min(3) @Max(16)
    private String nickname;
}

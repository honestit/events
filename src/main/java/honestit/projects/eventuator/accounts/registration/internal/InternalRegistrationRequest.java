package honestit.projects.eventuator.accounts.registration.internal;

import honestit.projects.eventuator.accounts.registration.RegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Locale;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class InternalRegistrationRequest implements RegistrationRequest {

    @NotBlank
    @Email
    private String username;
    @NotBlank
    @Size(min = 6, max = 12)
    private String password;
    @NotBlank
    @Size(min = 3, max = 16)
    private String nickname;
    private Locale locale;
}

package honestit.projects.eventuator.accounts.registration.internal;

import honestit.projects.eventuator.accounts.registration.RegistrationResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class InternalRegistrationResponse implements RegistrationResponse {

    private Long id;
    private boolean success;
    private Exception exception;
}

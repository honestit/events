package honestit.projects.eventuator.accounts.activation.internal;

import honestit.projects.eventuator.accounts.activation.ActivationResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class InternalActivationResponse implements ActivationResponse {

    private boolean success;
    private FailReason failReason;
}

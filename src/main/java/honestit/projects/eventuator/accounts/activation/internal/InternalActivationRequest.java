package honestit.projects.eventuator.accounts.activation.internal;

import honestit.projects.eventuator.accounts.activation.ActivationRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class InternalActivationRequest implements ActivationRequest {

    @NotNull
    @NotBlank
    @Length(min = 36, max = 36)
    private String tokenValue;
}

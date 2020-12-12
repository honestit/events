package honestit.projects.eventuator.accounts.registration.internal;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;

@Component
@ConfigurationProperties(prefix = "account.activation")
@Data
public class ActivationProperties {

    private Long tokenSeconds = ChronoUnit.DAYS.getDuration().toSeconds();
    private String mailAccount;
}

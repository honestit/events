package honestit.projects.eventuator.accounts.activation.internal;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;

@Component
@ConfigurationProperties(prefix = "account.activation")
@Data
public class AccountActivationProperties {

    private Long tokenSeconds = ChronoUnit.DAYS.getDuration().toSeconds();
    private String mailAccount = "no-reply@events.pl";
    private String appHost = "localhost";
    private Integer appPort = 8080;
}

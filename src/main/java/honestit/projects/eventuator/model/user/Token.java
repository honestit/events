package honestit.projects.eventuator.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class Token implements Serializable {

    @Column(name = "token_value", nullable = false)
    private String value;
    @Column(name = "token_created_on", nullable = false)
    private LocalDateTime createdOn;
    @Column(name = "token_active_till", nullable = false)
    private LocalDateTime activeTill;

    public boolean isValid(LocalDateTime when) {
        return when.isAfter(createdOn) && when.isBefore(activeTill);
    }

    public static Token generate(long time, TemporalUnit timeUnit) {
        LocalDateTime now = LocalDateTime.now();
        return Token.builder()
                .value(UUID.randomUUID().toString())
                .createdOn(now)
                .activeTill(now.plus(time, timeUnit))
                .build();
    }
}

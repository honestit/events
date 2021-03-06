package honestit.projects.eventuator.model.event;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_canceled_events")
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = true, of = "canceledOn") @ToString(callSuper = true)
public class CanceledEvent extends SubscribedEvent {

    private LocalDateTime canceledOn;
    private EventCancelReason reason;
}

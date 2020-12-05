package honestit.projects.events.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_canceled_events")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class CanceledEvent extends SubscribedEvent {

    private LocalDateTime cancelOn;
    private EventCancelReason reason;
}

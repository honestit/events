package honestit.projects.events.model.event;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_subscribed_events")
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = true, of = "subscribedOn") @ToString(callSuper = true)
public class SubscribedEvent extends Event {

    private LocalDateTime subscribedOn;
}

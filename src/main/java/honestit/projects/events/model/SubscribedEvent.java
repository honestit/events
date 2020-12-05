package honestit.projects.events.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.EventListener;

@Entity
@Table(name = "user_subscribed_events")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(callSuper = true, of = "subscribedOn") @ToString(callSuper = true)
public class SubscribedEvent extends Event {

    private LocalDateTime subscribedOn;
}

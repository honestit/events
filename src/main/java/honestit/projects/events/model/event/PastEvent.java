package honestit.projects.events.model.event;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_past_events")
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true)
public class PastEvent extends SubscribedEvent {

    private EventReview review;
    @Column(nullable = false)
    private Boolean participated;
}

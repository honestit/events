package honestit.projects.eventuator.model.event;

import honestit.projects.eventuator.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_subscribed_events")
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = true, of = {"subscribedOn", "userId"})
@ToString(callSuper = true, exclude = "user")
public class SubscribedEvent extends Event {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;
    private LocalDateTime subscribedOn;

    void setUser(User user) {
        this.user = user;
        this.userId = user.getId();
    }
}

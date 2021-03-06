package honestit.projects.eventuator.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EventCancelReason implements Serializable {

    private String reason;
}

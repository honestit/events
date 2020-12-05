package honestit.projects.events.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EventReview implements Serializable {

    @Column(nullable = false)
    private Integer rating;
    @Column(length = 0xffff)
    private String review;
}

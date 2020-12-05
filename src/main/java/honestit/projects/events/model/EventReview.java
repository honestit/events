package honestit.projects.events.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EventReview {

    @Column(nullable = false)
    private Integer rating;
    @Column(length = 0xffff)
    private String review;
}

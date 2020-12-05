package honestit.projects.events.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EventPlace {

    @Column(nullable = false)
    private String name;
    private String description;
    private String address;
}

package honestit.projects.events.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(of = {"name", "address"})
public class EventPlace implements Serializable {

    @Column(nullable = false)
    private String name;
    private String description;
    private String address;
}

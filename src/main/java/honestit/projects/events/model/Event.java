package honestit.projects.events.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode
@ToString
public class Event {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String name;
    private LocalDateTime when;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}

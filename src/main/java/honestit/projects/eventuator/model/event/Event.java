package honestit.projects.eventuator.model.event;

import honestit.projects.eventuator.model.common.BaseEntity;
import honestit.projects.eventuator.model.common.Identifiable;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Inheritance(strategy = InheritanceType.JOINED)
@SecondaryTable(name = "event_places", pkJoinColumns = @PrimaryKeyJoinColumn)

@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = {"name", "where", "when"}, callSuper = false)
@ToString(callSuper = true)
public class Event extends BaseEntity implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(table = "event_places")),
            @AttributeOverride(name = "description", column = @Column(table = "event_places")),
            @AttributeOverride(name = "address", column = @Column(table = "event_places"))
    })
    private EventPlace where;
    private LocalDateTime when;

}

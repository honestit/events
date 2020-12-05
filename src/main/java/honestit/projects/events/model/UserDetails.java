package honestit.projects.events.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserDetails {

    private String firstName;
    private String lastName;

}

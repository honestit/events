package honestit.projects.events.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@ToString(exclude = "password")
public class UserCredentials implements Serializable {

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role;
}

package honestit.projects.events.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@SecondaryTable(name = "user_credentials", pkJoinColumns = @PrimaryKeyJoinColumn)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String nickname;

    private UserDetails details;

    @AttributeOverrides({
            @AttributeOverride(name = "password", column = @Column(table = "user_credentials")),
            @AttributeOverride(name = "role", column = @Column(table = "user_credentials"))
    })
    private UserCredentials credentials;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}

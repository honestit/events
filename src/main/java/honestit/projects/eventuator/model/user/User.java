package honestit.projects.eventuator.model.user;

import honestit.projects.eventuator.model.common.BaseEntity;
import honestit.projects.eventuator.model.common.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@SecondaryTables({
        @SecondaryTable(name = "user_credentials", pkJoinColumns = @PrimaryKeyJoinColumn),
        @SecondaryTable(name = "user_activation_tokens", pkJoinColumns = @PrimaryKeyJoinColumn),
})
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class User extends BaseEntity implements Identifiable<Long> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String nickname;

    @Embedded
    private UserDetails details;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "password", column = @Column(table = "user_credentials")),
            @AttributeOverride(name = "role", column = @Column(table = "user_credentials")),
            @AttributeOverride(name = "active", column = @Column(table = "user_credentials")),
    })
    private UserCredentials credentials;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(table = "user_activation_tokens")),
            @AttributeOverride(name = "createdOn", column = @Column(table = "user_activation_tokens")),
            @AttributeOverride(name = "activeTill", column = @Column(table = "user_activation_tokens"))
    })
    private Token activationToken;
}

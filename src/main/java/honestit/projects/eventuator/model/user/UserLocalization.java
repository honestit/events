package honestit.projects.eventuator.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserLocalization {

    private String locale;
}

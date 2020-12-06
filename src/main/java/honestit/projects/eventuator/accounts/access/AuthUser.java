package honestit.projects.eventuator.accounts.access;

import java.util.Set;

public interface AuthUser {

    String getUsername();

    Set<String> getRoles();
}

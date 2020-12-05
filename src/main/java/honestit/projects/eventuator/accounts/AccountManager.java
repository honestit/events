package honestit.projects.eventuator.accounts;

import honestit.projects.eventuator.model.user.User;

public interface AccountManager {

    Long register(User user);
}

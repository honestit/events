package honestit.projects.eventuator.accounts.access;

import honestit.projects.eventuator.model.user.User;
import honestit.projects.eventuator.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j @RequiredArgsConstructor
public class DefaultAuthUser implements AuthUser {

    private final UserRepository userRepository;

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    @Transactional
    public boolean activate(String tokenValue) {
        log.debug("Activating user with token: {}", tokenValue);
        Optional<User> optionalUser = userRepository.getUserByActivationTokenValue(tokenValue);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.getCredentials().setActive(true);
            user.setActivationToken(null);
            log.debug("Activating user: {}", user);
            return true;
        }
        else {
            return false;
        }
    }
}

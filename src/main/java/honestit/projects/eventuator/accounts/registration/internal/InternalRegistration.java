package honestit.projects.eventuator.accounts.registration.internal;

import honestit.projects.eventuator.accounts.registration.Registration;
import honestit.projects.eventuator.accounts.registration.RegistrationResponse;
import honestit.projects.eventuator.model.user.User;
import honestit.projects.eventuator.model.user.UserConverter;
import honestit.projects.eventuator.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j @RequiredArgsConstructor
public class InternalRegistration implements Registration<InternalRegistrationRequest> {

    private final UserRepository userRepository;
    private final UserConverter converter;
    private final PasswordEncoder passwordEncoder;

    @Override @Transactional
    public RegistrationResponse register(InternalRegistrationRequest request) {
        log.debug("Registering user by request: {}", request);

        User user = converter.from(request);
        log.debug("Converted user: {}", user);

        if (userRepository.existsByUsername(user.getUsername())) {
            log.debug("Username already taken: {}", user.getUsername());
            return InternalRegistrationResponse.builder().success(false).exception(new UsernameAlreadyTakenException(user.getUsername())).build();
        }

        updateUserCredentials(user);

        userRepository.save(user);
        log.debug("Saved user: {}", user);
        return InternalRegistrationResponse.builder().success(true).id(user.getId()).build();
    }

    private void updateUserCredentials(User user) {
        user.getCredentials().setRole("ROLE_USER");
        user.getCredentials().setPassword(passwordEncoder.encode(user.getCredentials().getPassword()));
    }
}

package honestit.projects.eventuator.accounts.registration.internal;

import honestit.projects.eventuator.accounts.registration.Registration;
import honestit.projects.eventuator.accounts.registration.RegistrationResponse;
import honestit.projects.eventuator.model.user.Token;
import honestit.projects.eventuator.model.user.User;
import honestit.projects.eventuator.model.user.UserConverter;
import honestit.projects.eventuator.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;

@Service
@Slf4j @RequiredArgsConstructor
public class InternalRegistration implements Registration<InternalRegistrationRequest> {

    private final UserRepository userRepository;
    private final UserConverter converter;
    private final PasswordEncoder passwordEncoder;
    private final ActivationProperties activationProperties;
    private final JavaMailSender mailSender;
    private final ActivationSender activationSender;

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
        generateActivationToken(user);

        activationSender.sendActivationMail(user.getUsername(), user.getActivationToken().getValue(), request.getLocale());
        log.debug("Activation mail sending requested");
        userRepository.save(user);
        log.debug("Saved user: {}", user);
        return InternalRegistrationResponse.builder().success(true).id(user.getId()).build();
    }

    private void generateActivationToken(User user) {
        Token activationToken = Token.generate(activationProperties.getTokenSeconds(), ChronoUnit.SECONDS);
        user.setActivationToken(activationToken);
    }

    private void updateUserCredentials(User user) {
        user.getCredentials().setRole("ROLE_USER");
        user.getCredentials().setPassword(passwordEncoder.encode(user.getCredentials().getPassword()));
        user.getCredentials().setActive(false);
    }
}

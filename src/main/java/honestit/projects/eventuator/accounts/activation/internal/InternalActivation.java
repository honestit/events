package honestit.projects.eventuator.accounts.activation.internal;

import honestit.projects.eventuator.accounts.activation.Activation;
import honestit.projects.eventuator.accounts.activation.ActivationRequest;
import honestit.projects.eventuator.accounts.activation.ActivationResponse;
import honestit.projects.eventuator.model.user.Token;
import honestit.projects.eventuator.model.user.User;
import honestit.projects.eventuator.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

@Service
@Slf4j @RequiredArgsConstructor
public class InternalActivation implements Activation {

    private final AccountActivationProperties activationProperties;
    private final ActivationSender activationSender;
    private final UserRepository userRepository;
    private final Clock clock;

    @Override
    @Transactional
    public ActivationResponse activate(ActivationRequest activationRequest) {
        log.debug("Attempting to activate user with token {}", activationRequest.getTokenValue());
        return userRepository.getUserByActivationTokenValue(activationRequest.getTokenValue())
                .map(this::processActivation)
                .orElseGet(this::invalidToken);
    }

    private ActivationResponse processActivation(User user) {
        if (user.getCredentials().getActive()) {
            log.debug("Failing with activation. User {} is already active", user.getUsername());
            return alreadyActiveResponse();
        }
        else {
            Token activationToken = user.getActivationToken();
            if (!activationToken.isValid(LocalDateTime.now(clock))) {
                log.debug("Token has expired. Generating and sending new token for user {}", user.getUsername());
                generateAndResendActivationToken(user);
                return expiredTokenResponse();
            }
            else {
                activateUserAndDeleteToken(user);
                log.debug("Activation for user {} completed with success. Activation token {} was deleted", user.getUsername(), activationToken.getValue());
                return successResponse();
            }
        }
    }

    private InternalActivationResponse expiredTokenResponse() {
        return InternalActivationResponse.builder()
                .success(false)
                .failReason(ActivationResponse.FailReason.EXPIRED_TOKEN)
                .build();
    }

    private void generateAndResendActivationToken(User user) {
        Token activationToken;
        activationToken = Token.generate(activationProperties.getTokenSeconds(), ChronoUnit.SECONDS);
        user.setActivationToken(activationToken);
        log.debug("Saving new activation token {} for user {}", activationToken.getValue(), user.getUsername());
        activationSender.sendActivationMail(user.getUsername(), activationToken.getValue(), Locale.forLanguageTag(user.getLocalization().getLocale()));
    }

    private InternalActivationResponse successResponse() {
        return InternalActivationResponse.builder()
                .success(true)
                .build();
    }

    private void activateUserAndDeleteToken(User user) {
        user.getCredentials().setActive(true);
        user.setActivationToken(null);
    }

    private InternalActivationResponse alreadyActiveResponse() {
        return InternalActivationResponse.builder()
                .success(false)
                .failReason(ActivationResponse.FailReason.ALREADY_ACTIVE)
                .build();
    }

    private ActivationResponse invalidToken() {
        return InternalActivationResponse.builder()
                .success(false)
                .failReason(ActivationResponse.FailReason.INVALID_TOKEN)
                .build();
    }
}

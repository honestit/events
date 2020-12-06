package honestit.projects.eventuator.accounts.registration.internal;

import honestit.projects.eventuator.accounts.registration.Registration;
import honestit.projects.eventuator.accounts.registration.RegistrationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j @RequiredArgsConstructor
public class InternalRegistration implements Registration<InternalRegistrationRequest> {

    @Override
    public RegistrationResponse register(InternalRegistrationRequest request) {
        return null;
    }
}

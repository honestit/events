package honestit.projects.eventuator.accounts.registration;

public interface Registration<R extends RegistrationRequest> {

    RegistrationResponse register(R request);
}

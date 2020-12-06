package honestit.projects.eventuator.accounts.registration;

public interface RegistrationResponse {

    Long id();

    boolean success();

    Exception exception();
}

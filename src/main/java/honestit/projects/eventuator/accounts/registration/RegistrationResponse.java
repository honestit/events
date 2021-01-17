package honestit.projects.eventuator.accounts.registration;

public interface RegistrationResponse {

    Long getId();

    boolean isSuccess();

    Exception getException();
}

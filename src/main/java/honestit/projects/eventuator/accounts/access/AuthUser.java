package honestit.projects.eventuator.accounts.access;

public interface AuthUser {

    String getUsername();

    boolean activate(String token);
}

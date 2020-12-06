package honestit.projects.eventuator.accounts.registration.internal;

import lombok.Getter;

@Getter
public class UsernameAlreadyTakenException extends Exception {

    private String username;

    public UsernameAlreadyTakenException(String username) {
        super("Username " + username + " already taken");
        this.username = username;
    }
}

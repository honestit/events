package honestit.projects.eventuator.model.user;

import honestit.projects.eventuator.accounts.registration.internal.InternalRegistrationRequest;

public class UserConverter {

    public User from(InternalRegistrationRequest request) {
        return User.builder()
                .username(request.getUsername())
                .nickname(request.getNickname())
                .credentials(UserCredentials.builder()
                        .password(request.getPassword())
                        .build())
                .build();
    }
}

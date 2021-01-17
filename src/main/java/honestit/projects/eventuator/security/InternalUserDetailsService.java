package honestit.projects.eventuator.security;

import honestit.projects.eventuator.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Stream;

@Component
@Primary
@Slf4j
@RequiredArgsConstructor
public class InternalUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Stream.of(username)
                .peek(u -> log.debug("Requesting user by username: {}", u))
                .filter(userRepository::existsByUsername)
                .map(userRepository::getByUsername)
                .peek(user -> log.debug("Retrieved user: {}", user))
                .findFirst()
                .map(user -> new User(
                        user.getUsername(),
                        user.getCredentials().getPassword(),
                        user.getCredentials().getActive(),
                        true,
                        true,
                        true,
                        Collections.singletonList(new SimpleGrantedAuthority(user.getCredentials().getRole()))
                ))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}

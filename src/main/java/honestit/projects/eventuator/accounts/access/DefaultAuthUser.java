package honestit.projects.eventuator.accounts.access;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j @RequiredArgsConstructor
public class DefaultAuthUser implements AuthUser {

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean activate(String token) {
        return false;
    }
}

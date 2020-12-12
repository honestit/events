package honestit.projects.eventuator.web.pages.activation;

import honestit.projects.eventuator.accounts.access.AuthUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/activate")
@Slf4j
@RequiredArgsConstructor
public class ActivationUserController {

    public final AuthUser authUser;

    @GetMapping("/{token:.{36}}")
    public String processActivation(@PathVariable String token) {
        log.debug("Activating with token: {}", token);
        boolean activated = authUser.activate(token);
        if (activated) {
            return "activate/success";
        } else {
            return "redirect:/";
        }
    }
}

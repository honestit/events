package honestit.projects.eventuator.web.pages.activation;

import honestit.projects.eventuator.accounts.activation.Activation;
import honestit.projects.eventuator.accounts.activation.ActivationResponse;
import honestit.projects.eventuator.accounts.activation.internal.InternalActivationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
@RequestMapping("/activate")
@Slf4j
@RequiredArgsConstructor
public class UserActivationController {

    private final Activation activation;

    @GetMapping("/{token:.{36}}")
    public String processActivation(@PathVariable String token, Model model, Locale locale) {
        log.debug("Activating with token: {}", token);
        ActivationResponse response = activation.activate(InternalActivationRequest.builder()
                .tokenValue(token)
                .build());
        if (!response.isSuccess()) {
            model.addAttribute("failReason", response.getFailReason().name());
        }
        return "activate/result";
    }
}

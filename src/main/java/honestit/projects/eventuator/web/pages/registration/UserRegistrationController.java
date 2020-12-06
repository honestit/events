package honestit.projects.eventuator.web.pages.registration;

import honestit.projects.eventuator.accounts.registration.Registration;
import honestit.projects.eventuator.accounts.registration.RegistrationResponse;
import honestit.projects.eventuator.accounts.registration.internal.InternalRegistrationRequest;
import honestit.projects.eventuator.web.utils.Errors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
@Slf4j @RequiredArgsConstructor
public class UserRegistrationController {

    private final Registration<InternalRegistrationRequest> registration;

    @GetMapping
    public String preparePage(Model model) {
        log.trace("Preparing registration page");
        model.addAttribute("registration", new InternalRegistrationRequest());
        return "registration/form";
    }

    @PostMapping
    public String processPage(@ModelAttribute("registration") @Valid InternalRegistrationRequest request, BindingResult bindings) {
        log.debug("Registration request: {}", request);

        if (bindings.hasErrors()) {
            log.debug("Registration request has errors: {}", Errors.listOf(bindings));
            return "registration/form";
        }

        registration.register(request);
        return "registration/success";
    }

    @ExceptionHandler(Exception.class)
    public String processException(Exception exception, Model model) {
        log.warn("Exception during registration: {}", exception.getLocalizedMessage());
        model.addAttribute("registration", new InternalRegistrationRequest());
        model.addAttribute("error", "registration-error");
        return "registration/form";
    }
}

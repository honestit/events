package honestit.projects.eventuator.web.pages.registration;

import honestit.projects.eventuator.accounts.registration.Registration;
import honestit.projects.eventuator.accounts.registration.RegistrationResponse;
import honestit.projects.eventuator.accounts.registration.internal.InternalRegistrationRequest;
import honestit.projects.eventuator.web.utils.Errors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Locale;

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
    public String processPage(@ModelAttribute("registration") @Valid InternalRegistrationRequest request, BindingResult bindings, Locale locale) {
        log.debug("Registration request: {}", request);

        if (bindings.hasErrors()) {
            log.debug("Registration request has errors: {}", Errors.listOf(bindings));
            return "registration/form";
        }

        request.setLocale(locale);
        RegistrationResponse response = registration.register(request);
        if (response.isSuccess()) {
            return "registration/success";
        }
        else {
            bindings.reject("registration-logic-error", response.getException().getLocalizedMessage());
            return "registration/form";
        }
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView processException(Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        log.warn("Exception during registration: {}", exception.getLocalizedMessage());
        modelAndView.addObject("registration", new InternalRegistrationRequest());
        modelAndView.addObject("error", "registration-error");
        modelAndView.setViewName("registration/form");
        return modelAndView;
    }
}

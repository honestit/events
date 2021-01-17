package honestit.projects.eventuator.web.utils;

import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

public class Errors {

    public static List<String> listOf(BindingResult bindings) {
        if (bindings == null || !bindings.hasErrors()) {
            return List.of();
        }
        return bindings.getFieldErrors().stream()
                .map(fieldError -> String.format("%s : %s",
                        fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
    }
}

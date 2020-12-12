package honestit.projects.eventuator.accounts.registration.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Component
@Slf4j @RequiredArgsConstructor
public class ActivationSender {

    private final ActivationProperties activationProperties;
    private final JavaMailSender mailSender;
    private final MessageSource messageSource;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendActivationMail(String username, String tokenValue) {
        log.info("Sending activation message to {}", username);
        try {
            sendActivation(username, tokenValue);
        } catch (MessagingException messagingException) {
            log.warn("Error while sending activation message to " + username, messagingException);
            throw new RuntimeException("Error while sending activation message to " + username, messagingException);
        }
        log.info("Activation message sent");
    }

    private void sendActivation(String username, String tokenValue) throws MessagingException {
        MimeMessage activationMail = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(activationMail);
        helper.setFrom(activationProperties.getMailAccount());
        helper.setTo(username);
        String mailSubject = messageSource.getMessage("mail.activation.mail-title", null, Locale.getDefault());
        helper.setSubject(mailSubject);
        Context context = new Context(Locale.getDefault());
        context.setVariable("username", username);
        context.setVariable("tokenValue", tokenValue);
        context.setVariable("address", activationProperties.getAppHost() + ":" + activationProperties.getAppPort());
        String mailBody = templateEngine.process("mail/activation", context);
        helper.setText(mailBody, true);
        mailSender.send(activationMail);
    }
}

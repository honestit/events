package honestit.projects.eventuator.accounts.registration.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@Slf4j @RequiredArgsConstructor
public class ActivationSender {

    private final ActivationProperties activationProperties;
    private final JavaMailSender mailSender;

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
        helper.setFrom("no-reply@eventuator.com");
        helper.setTo(username);
        helper.setSubject("Activate your account");
        String mailMessage = String.format("<a href='%s'>Aktywuj</a>", "http://localhost:8080/activate/" + tokenValue);
        helper.setText(mailMessage, true);
        mailSender.send(activationMail);
    }
}

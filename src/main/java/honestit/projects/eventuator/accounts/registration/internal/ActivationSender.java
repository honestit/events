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

    private final AccountActivationProperties activationProperties;
    private final JavaMailSender mailSender;
    private final MessageSource messageSource;
    private final SpringTemplateEngine templateEngine;

    private String MAIL_TEMPLATE_LOCATION = "mail/activation";
    private String MAIL_SUBJECT_MESSAGE_CODE = "mail.activation.mail-title";

    @Async
    public void sendActivationMail(String username, String tokenValue, Locale locale) {
        log.info("Sending activation message to {}", username);
        try {
            sendActivation(username, tokenValue, locale);
        } catch (MessagingException messagingException) {
            log.warn("Error while sending activation message to " + username, messagingException);
            throw new RuntimeException("Error while sending activation message to " + username, messagingException);
        }
        log.info("Activation message sent to {}", username);
    }

    private void sendActivation(String username, String tokenValue, Locale locale) throws MessagingException {
        MimeMessage activationMail = mailSender.createMimeMessage();
        MimeMessageHelper helper = getBaseMimeMessageHelper(username, activationMail);
        String mailSubject = getMailSubject(locale);
        log.trace("Sending mail to {} with title {}", username, mailSubject);
        helper.setSubject(mailSubject);
        String mailBody = getMailBody(username, tokenValue, locale);
        log.trace("Sending mail to {} with body {}", username, mailBody);
        helper.setText(mailBody, true);
        mailSender.send(activationMail);
    }

    private String getMailBody(String username, String tokenValue, Locale locale) {
        Context context = new Context(locale);
        context.setVariable("username", username);
        context.setVariable("tokenValue", tokenValue);
        context.setVariable("address", activationProperties.getAppHost() + ":" + activationProperties.getAppPort());
        return templateEngine.process(MAIL_TEMPLATE_LOCATION, context);
    }

    private String getMailSubject(Locale locale) {
        return messageSource.getMessage(MAIL_SUBJECT_MESSAGE_CODE, null, locale);
    }

    private MimeMessageHelper getBaseMimeMessageHelper(String username, MimeMessage activationMail) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(activationMail);
        helper.setFrom(activationProperties.getMailAccount());
        helper.setTo(username);
        return helper;
    }
}

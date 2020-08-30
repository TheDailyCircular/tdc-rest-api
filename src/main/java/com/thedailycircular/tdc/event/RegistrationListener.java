package com.thedailycircular.tdc.event;

import com.thedailycircular.tdc.model.User;
import com.thedailycircular.tdc.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.thedailycircular.tdc.security.SecurityConstants.*;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserServices userServices;

    @Autowired
    private Environment environment;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userServices.createEmailVerificationToken(user, token);

        final SimpleMailMessage email = constructEmailMessage(event, user, token);
        try {
            mailSender.send(email);
        } catch (Exception ex) {
            throw ex;
        }
    }

    private SimpleMailMessage constructEmailMessage(
            final OnRegistrationCompleteEvent event, final User user, final String token) {

        final String recipientAddress = user.getUsername();
        final String subject = EMAIL_CONFIRMATION_SUBJECT;
        final String confirmationUrl = event.getAppUrl() + EMAIL_CONFIRMATION_URL + ".html?token=" + token;
        final String message = messageSource.getMessage(
                "message.regSuccLink",
                null,
                "You registered successfully. To confirm your registration, please click on the below link.",
                event.getLocale()
        );
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(environment.getProperty("spring.mail.username"));
        return email;
    }
}

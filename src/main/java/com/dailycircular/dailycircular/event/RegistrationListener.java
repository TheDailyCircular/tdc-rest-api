package com.dailycircular.dailycircular.event;

import com.dailycircular.dailycircular.model.ApplicationUser;
import com.dailycircular.dailycircular.security.CustomUserDetailServices;
import com.dailycircular.dailycircular.service.ApplicationUserServices;
import com.dailycircular.dailycircular.service.MailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.dailycircular.dailycircular.security.SecurityConstants.*;


@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final MessageSource messageSource;

    private final MailServices mailServices;

    private final ApplicationUserServices applicationUserServices;

    public RegistrationListener(
            MessageSource messageSource,
            MailServices mailServices,
            ApplicationUserServices applicationUserServices) {

        this.messageSource = messageSource;
        this.mailServices = mailServices;
        this.applicationUserServices = applicationUserServices;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        ApplicationUser applicationUser = event.getApplicationUser();
        String token = UUID.randomUUID().toString();
        applicationUserServices.createEmailVerificationToken(applicationUser, token);

        final SimpleMailMessage email = constructEmailMessage(event, applicationUser, token);

        mailServices.sendMail(email);
    }

    private SimpleMailMessage constructEmailMessage(
            final OnRegistrationCompleteEvent event, final ApplicationUser applicationUser, final String token) {

        final String recipientAddress = applicationUser.getUsername();
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
        email.setFrom(EMAIL_CONFIRMATION_SENDER);
        return email;
    }
}

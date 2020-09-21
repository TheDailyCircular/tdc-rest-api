package com.dailycircular.dailycircular.mail;

import com.dailycircular.dailycircular.model.ApplicationUser;
import com.dailycircular.dailycircular.model.EmailVerificationToken;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import static com.dailycircular.dailycircular.constants.DailyCircularURLs.EMAIL_VERIFICATION_LINK;
import static com.dailycircular.dailycircular.constants.MailConstants.EMAIL_VERIFICATION_TOKEN_EMAIL_SUBJECT;

@Component
public class MailServices {

    private final JavaMailSender javaMailSender;

    public MailServices(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(SimpleMailMessage mailMessage) {
        try {
            javaMailSender.send(mailMessage);
        } catch (MailAuthenticationException ex) {
            System.err.println("MailAuthenticationException");
            throw ex;
        } catch (MailSendException ex) {
            System.err.println("MailSendException");
            throw ex;
        } catch (MailException ex) {
            System.err.println("MailException");
            throw ex;
        } catch (Exception ex) {
            System.err.println("Exception");
            throw ex;
        }
    }

    public void sendEmailVerificationTokenMail(ApplicationUser applicationUser, EmailVerificationToken emailVerificationToken) {
        SimpleMailMessage emailVerificationTokenMail = new SimpleMailMessage();
        emailVerificationTokenMail.setTo(applicationUser.getUsername());
        emailVerificationTokenMail.setSubject(EMAIL_VERIFICATION_TOKEN_EMAIL_SUBJECT);
        emailVerificationTokenMail.setText(getEmailVerificationTokenMailText(emailVerificationToken.getToken()));
        sendMail(emailVerificationTokenMail);
    }

    private String getEmailVerificationTokenMailText(String token) {
        return "<h3>Welcome to Daily Circular.</h3>\n\n" +
                "Your email verification code is " + token + ". " +
                "Go to the following link for email confirmation.\n" +
                EMAIL_VERIFICATION_LINK;
    }

}

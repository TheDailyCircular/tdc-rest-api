package com.dailycircular.dailycircular.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailServices {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(SimpleMailMessage mailMessage, String recipientEmail) {
        mailMessage.setTo(recipientEmail);

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
}

package com.dailycircular.dailycircular.api;

import com.dailycircular.dailycircular.mail.MailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/test")
public class TestController {

    @Autowired
    private MailServices mailServices;

    @GetMapping("/sendmail")
    public String testMailSend() {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Test mail from Daily Circular");
        message.setText("Dear Sir,\n\nThis mail has sent for testing purpose. Thank you.");
        mailServices.sendMail(message, "kmtusher97@gmail.com");
        return "sent!!";
    }
}

package com.dailycircular.dailycircular.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

import static com.dailycircular.dailycircular.mail.MailConstants.BASE_PACKAGE_MAIL;

//@Configuration
//@ComponentScan(basePackages = {BASE_PACKAGE_MAIL})
public class MailConfiguration {

//    private final Environment environment;
//
//    public MailConfiguration(Environment environment) {
//        this.environment = environment;
//    }
//
//    @Bean
//    public JavaMailSender getJavaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//
//        mailSender.setHost(environment.getProperty("spring.mail.host"));
//        mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
//
//        mailSender.setUsername(environment.getProperty("spring.mail.username"));
//        mailSender.setPassword(environment.getProperty("spring.mail.password"));
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", environment.getProperty("spring.mail.properties.transport.protocol"));
//        props.put("mail.smtp.auth", environment.getProperty("spring.mail.properties.mail.smtp.auth"));
//        props.put("mail.smtp.starttls.enable", environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
//        props.put("mail.debug", environment.getProperty("spring.mail.properties.mail.debug"));
//
//        return mailSender;
//    }
}

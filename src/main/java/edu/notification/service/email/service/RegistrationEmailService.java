package edu.notification.service.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class RegistrationEmailService {

    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    @Autowired
    public RegistrationEmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    private static final Logger log = LoggerFactory.getLogger(RegistrationEmailService.class);


    @Value("${spring.mail.username}")
    private String from;

    public void registerUserEmail(String to, String code) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String subject = "Email Verification";
        String content = buildEmailContent(code);

        helper.setTo(to);
        helper.setFrom(from);
        helper.setSubject(subject);
        helper.setText(content, true);

        log.info("Sending an email: email={}", to);
        mailSender.send(message);
    }

    private String buildEmailContent(String code) {

        Context context = new Context();
        context.setVariable("code", code);

        return templateEngine.process("registration-email", context);
    }
}

package edu.notification.service.email.utils;

import edu.notification.service.email.service.RegistrationEmailService;
import edu.notification.service.email.dto.RegistrationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;


@Component
public class RegistrationEventListener {

    private static final Logger log = LoggerFactory.getLogger(RegistrationEventListener.class);

    private final RegistrationEmailService registrationEmailService;

    @Autowired
    public RegistrationEventListener(RegistrationEmailService registrationEmailService) {
        this.registrationEmailService = registrationEmailService;
    }

    @EventListener
    public void handleRegistrationEvent(RegistrationEvent event) throws MessagingException {
        registrationEmailService.registerUserEmail(event.email(), event.message());
        log.info("Handled registration event for email: {}, code: {}", event.email(), event.message());
    }
}

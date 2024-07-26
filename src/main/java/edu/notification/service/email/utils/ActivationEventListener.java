package edu.notification.service.email.utils;

import edu.notification.service.email.dto.ActivationEvent;
import edu.notification.service.email.service.ActivationEmailService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class ActivationEventListener {

    private static final Logger log = LoggerFactory.getLogger(ActivationEventListener.class);

    private final ActivationEmailService activationEmailService;

    @Autowired
    public ActivationEventListener(ActivationEmailService activationEmailService) {
        this.activationEmailService = activationEmailService;
    }

    @EventListener
    public void handleActivationEvent(ActivationEvent event) throws MessagingException {
        activationEmailService.activateUserEmail(event.email());
        log.info("Handled registration event for email: {}", event.email());
    }
}

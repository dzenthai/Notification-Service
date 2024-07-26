package edu.notification.service.email.utils;

import edu.notification.service.email.dto.RegistrationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


@Service
public class RegistrationEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public RegistrationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishEmailEvent(String email, String message) {
        RegistrationEvent event = new RegistrationEvent(email, message);
        eventPublisher.publishEvent(event);
    }
}

package edu.notification.service.email.utils;

import edu.notification.service.email.dto.ActivationEvent;
import edu.notification.service.email.dto.RegistrationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


@Service
public class ActivationEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public ActivationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishEmailEvent(String email) {
        ActivationEvent event = new ActivationEvent(email);
        eventPublisher.publishEvent(event);
    }
}

package edu.notification.service.email.kafka;

import edu.notification.service.email.utils.ActivationEventPublisher;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserActivationConsumer {

    private static final Logger log = LoggerFactory.getLogger(UserRegistrationConsumer.class);

    private final ActivationEventPublisher activationEventPublisher;

    @Autowired
    public UserActivationConsumer(ActivationEventPublisher activationEventPublisher) {
        this.activationEventPublisher = activationEventPublisher;
    }

    @KafkaListener(topics = "activation-notification", groupId = "email-verified")
    public void emailConfirmationListen(String record) throws MessagingException {

        try {

            activationEventPublisher.publishEmailEvent(record);
            log.info("Verification code has been sent for email: {}", record);

        } catch (Exception e) {
            log.error("Failed to process message: {}", record, e);
        }
    }
}

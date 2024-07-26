package edu.notification.service.email.kafka;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.notification.service.email.utils.RegistrationEventPublisher;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class UserRegistrationConsumer {

    private static final Logger log = LoggerFactory.getLogger(UserRegistrationConsumer.class);

    private final RegistrationEventPublisher eventPublisher;

    @Autowired
    public UserRegistrationConsumer(RegistrationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @KafkaListener(topics = "registration-notification", groupId = "email-confirmation")
    public void emailConfirmationListen(String record) throws MessagingException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, String> recordMap = objectMapper.readValue(record, new TypeReference<Map<String, String>>() {});

            String email = recordMap.get("email");
            String code = recordMap.get("code");

            eventPublisher.publishEmailEvent(email, code);
            log.info("Verification code has been sent for email: {}, code: {}", email, code);

        } catch (Exception e) {
            log.error("Failed to process message: {}", record, e);
        }
    }
}

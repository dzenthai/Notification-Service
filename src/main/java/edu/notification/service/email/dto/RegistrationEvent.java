package edu.notification.service.email.dto;


public record RegistrationEvent
        (
                String email,
                String message
        )
{}

package com.example.config.security.handler;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEventsHandler {

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        Authentication authentication = success.getAuthentication();
        System.out.println("AuthenticationSuccessEvent: login success: " + authentication.getName());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failures) {
        AuthenticationException exception = failures.getException();
        System.out.println("AbstractAuthenticationFailureEvent: login fail: " + exception.getMessage());
    }
}

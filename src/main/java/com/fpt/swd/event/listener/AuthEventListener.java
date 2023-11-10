package com.fpt.swd.event.listener;


import com.fpt.swd.business.EmailService;
import com.fpt.swd.event.OnAccountActivation;
import com.fpt.swd.event.OnAccountCreation;
import com.fpt.swd.event.OnAccountRecover;
import com.fpt.swd.event.OnResendActivationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AuthEventListener {

    private final EmailService emailService;

    @Autowired
    public AuthEventListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @Async
    @EventListener
    public void handleOnAccountCreation(OnAccountCreation event) {
        emailService.sendActivationEmail(event.getUser());
    }

    @Async
    @EventListener
    public void handleOnAccountActivation(OnAccountActivation event) {
        emailService.sendWelcomeEmail(event.getUser());
    }

    @Async
    @EventListener
    public void handleOnAccountRecover(OnAccountRecover event) {
        emailService.sendRecoveryEmail(event.getUser());
    }

    @Async
    @EventListener
    public void handleOnResendActivationToken(OnResendActivationToken event) {
        emailService.sendActivationEmail(event.getUser());
    }
}

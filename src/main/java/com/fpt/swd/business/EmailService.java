package com.fpt.swd.business;


import com.fpt.swd.business.impl.EmailServiceImpl;
import com.fpt.swd.database.entity.User;

import java.util.Map;

public interface EmailService {
    void sendActivationEmail(User user);

    void sendEmailTo(User user, EmailServiceImpl.EmailType emailType, Map<String, Object> emailArguments);

    void sendWelcomeEmail(User user);

    void sendRecoveryEmail(User user);
}

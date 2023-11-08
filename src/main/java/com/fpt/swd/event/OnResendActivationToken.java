package com.fpt.swd.event;


import com.fpt.swd.database.entity.User;
import org.springframework.context.ApplicationEvent;

public class OnResendActivationToken extends ApplicationEvent {
    private User user;

    public OnResendActivationToken(User user) {
        super(user);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}

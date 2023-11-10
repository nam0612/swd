package com.fpt.swd.event;

import com.fpt.swd.database.entity.User;
import org.springframework.context.ApplicationEvent;

public class OnAccountRecover extends ApplicationEvent {

    private User user;

    public OnAccountRecover(User user) {
        super(user);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}

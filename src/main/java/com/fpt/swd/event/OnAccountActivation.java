package com.fpt.swd.event;


import com.fpt.swd.database.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class OnAccountActivation extends ApplicationEvent {
    @Getter
    private User user;

    public OnAccountActivation(User user) {
        super(user);
        this.user = user;
    }
}

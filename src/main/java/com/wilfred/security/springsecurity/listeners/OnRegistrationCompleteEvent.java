package com.wilfred.security.springsecurity.listeners;

import com.wilfred.security.springsecurity.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


import java.util.Locale;

@Getter
@Setter
@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent  extends ApplicationEvent {
    private final String appUrl;
    private final Locale locale;
    private final User user;

    public OnRegistrationCompleteEvent(final User user, final Locale locale, final String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }



}

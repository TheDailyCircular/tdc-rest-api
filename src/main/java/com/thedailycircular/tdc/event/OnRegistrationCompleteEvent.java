package com.thedailycircular.tdc.event;

import com.thedailycircular.tdc.model.ApplicationUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String appUrl;
    private Locale locale;
    private ApplicationUser applicationUser;

    public OnRegistrationCompleteEvent(ApplicationUser applicationUser, Locale locale, String appUrl) {
        super(applicationUser);

        this.appUrl = appUrl;
        this.locale = locale;
        this.applicationUser = applicationUser;
    }
}

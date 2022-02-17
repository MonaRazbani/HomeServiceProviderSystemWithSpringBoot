package ir.maktab.homeserviceprovidersystemwithspringboot.dto;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.roles.User;
import lombok.*;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;
@Getter
@Setter
public class OnRegistrationCompleteEventDto  extends ApplicationEvent {
    private String appUrl;
    private Locale locale;
    private User user;
    public OnRegistrationCompleteEventDto(
            User user, Locale locale, String appUrl) {
        super(user);

        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

}

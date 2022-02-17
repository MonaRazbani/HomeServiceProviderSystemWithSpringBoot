package ir.maktab.homeserviceprovidersystemwithspringboot.dto;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.roles.User;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.UserService;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements
        ApplicationListener<OnRegistrationCompleteEventDto> {

    @Autowired
    private UserService userService;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEventDto event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEventDto event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        verificationTokenService.save(user,token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl
                = event.getAppUrl() + "/regitrationConfirm.html?token=" + token;
        String message = messages.getMessage("message.regSucc", null, event.getLocale());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
    }
}
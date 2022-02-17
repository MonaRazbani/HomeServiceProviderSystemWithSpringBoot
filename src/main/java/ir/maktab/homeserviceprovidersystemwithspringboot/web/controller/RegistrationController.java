package ir.maktab.homeserviceprovidersystemwithspringboot.web.controller;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.VerificationToken;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.roles.User;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.enums.UserStatus;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.UserService;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;

@Configuration
@RequiredArgsConstructor
public class RegistrationController {

    private final VerificationTokenService verificationTokenService;
    private final UserService userService;

    @GetMapping("/regitrationConfirm")
    public String confirmRegistration
            (WebRequest request, Model model, @RequestParam("token") String token) {

        Locale locale = request.getLocale();

        VerificationToken verificationToken = verificationTokenService.findByToken(token);

        User user = verificationToken.getUser();

        user.setStatus(UserStatus.WAITING_FOR_CONFIRM);
        userService.update(user);
        return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
    }
}

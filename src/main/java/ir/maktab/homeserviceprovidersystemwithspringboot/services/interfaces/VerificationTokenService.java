package ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.VerificationToken;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.roles.User;

public interface VerificationTokenService {
    VerificationToken save (User user , String token );
    VerificationToken findByToken (String token );
}

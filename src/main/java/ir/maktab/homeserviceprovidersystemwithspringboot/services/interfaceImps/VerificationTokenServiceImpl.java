package ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaceImps;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.dao.VerificationTokenDao;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.VerificationToken;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.roles.User;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.verificationTokenNotFound;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenDao verificationTokenDao;

    @Override
    public VerificationToken save(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        return verificationTokenDao.save(verificationToken);
    }

    @Override
    public VerificationToken findByToken(String token) {
        Optional<VerificationToken> verificationTokenOptional = verificationTokenDao.findByToken(token);
        if(verificationTokenOptional.isEmpty())
            throw new verificationTokenNotFound();
        return verificationTokenOptional.get();
    }
}

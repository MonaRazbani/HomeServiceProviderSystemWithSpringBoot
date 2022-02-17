package ir.maktab.homeserviceprovidersystemwithspringboot.data.dao;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.VerificationToken;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.roles.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VerificationTokenDao extends CrudRepository<VerificationToken,Long> {

    Optional<VerificationToken> findByToken( String token);
    Optional<VerificationToken> findByUser(User user);
}

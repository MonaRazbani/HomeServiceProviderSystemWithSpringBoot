package ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.roles.User;

public interface UserService {
    User findByEmail(String email);

    void update(User user);

}

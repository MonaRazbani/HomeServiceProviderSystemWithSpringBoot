package ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaceImps;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.dao.UserDao;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.roles.User;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.UserNotFound;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final ModelMapper modelMapper;

    @Override
    public User findByEmail(String email) {
        Optional<User> userOptional = userDao.findByEmail(email);
        if (userOptional.isEmpty())
            throw new UserNotFound();
        return userOptional.get();
    }

    @Override
    public void update(User user) {
        userDao.save(user);
    }
}

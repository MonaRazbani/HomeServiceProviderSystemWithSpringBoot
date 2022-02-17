package ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaceImps;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.dao.AdminDao;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.dao.UserDao;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDao userDao;
    private final AdminDao adminDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.roles.User> optionalUser
                = userDao.findByEmail(username);
        if (optionalUser.isPresent()) {
            switch (optionalUser.get().getRoleType()) {
                case CUSTOMER: {
                    return User.withUsername(optionalUser.get().getEmail())
                            .password(optionalUser.get().getPassword())
                            .roles("CUSTOMER")
                            .build();
                }
                case EXPERT: {
                    return User.withUsername(optionalUser.get().getEmail())
                            .password(optionalUser.get().getPassword())
                            .roles("EXPERT")
                            .build();
                }
            }
        } else {
            Optional<Admin> optionalAdmin = adminDao.findByUsername(username);
            if (optionalAdmin.isPresent()) {
                return User.withUsername(optionalAdmin.get().getUsername())
                        .password(optionalAdmin.get().getPassword())
                        .roles("ADMIN")
                        .build();
            }
        }
        throw new UsernameNotFoundException("user not found ");
    }

}

package ir.maktab.homeserviceprovidersystemwithspringboot.services.userDetialServices;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.dao.AdminDao;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminDetailService implements UserDetailsService {

    private final AdminDao adminDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> optionalUser = adminDao.findByUsername(username);

        if (optionalUser.isPresent()) {

            return org.springframework.security.core.userdetails.User.withUsername(optionalUser.get().getUsername())
                    .password(optionalUser.get().getPassword())
                    .roles("ADMIN")
                    .build();
        }
        throw new UsernameNotFoundException("user not found ");
    }

}


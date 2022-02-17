package ir.maktab.homeserviceprovidersystemwithspringboot.services.userDetialServices;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.dao.ExpertDao;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.roles.Expert;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ExpertDetailService implements UserDetailsService {

    private final ExpertDao expertDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Expert> optionalUser = expertDao.findByEmail(username);

        if (optionalUser.isPresent()) {

            return org.springframework.security.core.userdetails.User.withUsername(optionalUser.get().getEmail())
                    .password(optionalUser.get().getPassword())
                    .roles("EXPERT")
                    .build();
        }
        throw new UsernameNotFoundException("user not found ");
    }

}



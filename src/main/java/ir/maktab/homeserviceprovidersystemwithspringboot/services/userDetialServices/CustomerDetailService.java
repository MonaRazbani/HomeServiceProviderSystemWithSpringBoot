package ir.maktab.homeserviceprovidersystemwithspringboot.services.userDetialServices;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.dao.CustomerDao;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.roles.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerDetailService implements UserDetailsService {

    private final CustomerDao customerDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> optionalUser = customerDao.findByEmail(username);

        if (optionalUser.isPresent()) {

            return org.springframework.security.core.userdetails.User.withUsername(optionalUser.get().getEmail())
                    .password(optionalUser.get().getPassword())
                    .roles("CUSTOMER")
                    .build();
        }
        throw new UsernameNotFoundException("user not found ");
    }

}

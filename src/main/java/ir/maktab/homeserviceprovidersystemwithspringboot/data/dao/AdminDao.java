package ir.maktab.homeserviceprovidersystemwithspringboot.data.dao;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminDao extends CrudRepository<Admin ,Long>  {

    Optional<Admin> findByUsername(String username);

    Optional<Admin> findByUsernameAndPassword(String username, String password);
}

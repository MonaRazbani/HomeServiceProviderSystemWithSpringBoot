package ir.maktab.homeserviceprovidersystemwithspringboot.data.dao;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressDao extends CrudRepository<Address,Long> {
    Optional<Address> findByIdentificationCode(UUID identificationCode);
}

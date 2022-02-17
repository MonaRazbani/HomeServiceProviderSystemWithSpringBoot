package ir.maktab.homeserviceprovidersystemwithspringboot.data.dao;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceCategoryDao extends JpaRepository<ServiceCategory,Long>, JpaSpecificationExecutor<ServiceCategory> {

    Optional<ServiceCategory> findByName(String name);
}

package ir.maktab.homeserviceprovidersystemwithspringboot.data.dao;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.Offer;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.Order;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.roles.Expert;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.enums.OfferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfferDao extends JpaRepository<Offer,Long> {
    Optional<Offer> findByIdentificationCode(UUID identificationCode);
    List<Offer> findByOrder(Order order);
    List<Offer> findByOrderOrderBySuggestedPriceAsc(Order order);
    List<Offer>findByExpert(Expert expert);
    Optional<Offer> findByOrderAndStatus(Order order, OfferStatus offerStatus);
}
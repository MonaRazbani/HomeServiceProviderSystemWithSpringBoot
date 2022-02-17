package ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.Offer;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.OfferDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.OrderDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.roles.ExpertDto;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

public interface OfferService {
     OfferDto saveOffer(OfferDto offerDto) throws ParseException;

    OfferDto editStartDateOffer(OfferDto offerDto, String startDate) throws ParseException;

    OfferDto editOfferSuggestedPrice(OfferDto offerDto, String suggestedPrice) throws ParseException;

    OfferDto editSuggestedDurationOfService(OfferDto offerDto, String suggestedDurationOfService)throws ParseException;

    OfferDto changeOrderStatus (OfferDto offerDto ,String orderStatus) throws ParseException;

    List<OfferDto> findOfferDtosOfOrder(OrderDto orderDto);

    OfferDto findAcceptedOfferOfOrder (OrderDto orderDto);

    void deleteOfferFromOrder(OfferDto offerDto) throws ParseException;

    void acceptOfferForOrder(Offer offer) throws ParseException;

    List<OfferDto> findByOfferSortedByPriceAndExpertRate(OrderDto orderDto);

    Offer updateOffer(OfferDto offerDto) throws ParseException;

    Offer findOfferByIdentificationCode(UUID identificationCode);

    OfferDto findOfferDtoByIdentificationCode(UUID identificationCode);

    long findOfferId(UUID identificationCode);

    Offer findOfferById(long id) ;

    List<OfferDto> findExpertOffer(String expertEmail);
}


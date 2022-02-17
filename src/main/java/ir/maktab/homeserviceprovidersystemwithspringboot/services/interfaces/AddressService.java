package ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.Address;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.AddressDto;

import java.util.UUID;

public interface AddressService {
    AddressDto saveAddress(AddressDto addressDto);

    AddressDto updateAddress(AddressDto addressDto);

    Address findAddressById(long id);

    Address findAddressByIdentificationCode(UUID identificationCode);

    long findAddressId(UUID identificationCode);

}

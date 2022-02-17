package ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaceImps;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.dao.AddressDao;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.Address;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.AddressDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.AddressNotFound;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressServiceImp implements AddressService {
    private final AddressDao addressDao;
    private final ModelMapper modelMapper;



    @Override
    public AddressDto saveAddress(AddressDto addressDto) {
        Address address = modelMapper.map(addressDto, Address.class);
        address.setIdentificationCode(UUID.randomUUID());
        Address save = addressDao.save(address);
        return modelMapper.map(save, AddressDto.class);
    }

    @Override
    public AddressDto updateAddress(AddressDto addressDto) {

        Address address = modelMapper.map(addressDto, Address.class);
        long addressId = findAddressId(addressDto.getIdentificationCode());
        address.setId(addressId);
        Address save = addressDao.save(address);
        return modelMapper.map(save, AddressDto.class);
    }

    @Override
    public Address findAddressById(long id) {
        Optional<Address> address = addressDao.findById(id);
        if (address.isPresent())
            return address.get();
        else
            throw new AddressNotFound();
    }

    @Override
    public Address findAddressByIdentificationCode(UUID identificationCode) {
        Optional<Address> address = addressDao.findByIdentificationCode(identificationCode);
        if (address.isPresent())
            return address.get();
        else
            throw new AddressNotFound();
    }

    @Override
    public long findAddressId(UUID identificationCode) {
        Address address = findAddressByIdentificationCode(identificationCode);
        return address.getId();
    }
}

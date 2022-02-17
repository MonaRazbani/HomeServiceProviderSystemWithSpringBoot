package ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.roles.Customer;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.roles.CustomerDto;

public interface CustomerService {

    CustomerDto saveCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(CustomerDto customerDto);

    Customer updateCustomer(Customer customer);

    CustomerDto loginCustomer(CustomerDto customerDto);

    Customer findCustomerByEmail(String email);

    CustomerDto findCustomerDtoByEmail(String email);

    void changePasswordForCustomer(CustomerDto customerDto, String currentPassword, String newPassword);

    long findCustomerId(String email);


}

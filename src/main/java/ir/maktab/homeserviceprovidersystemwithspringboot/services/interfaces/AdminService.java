package ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces;

import ir.maktab.homeserviceprovidersystemwithspringboot.dto.filterDto.OrderCategoryDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.filterDto.UserCategoryDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.AdminDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.OrderDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.roles.UserDto;

import java.util.List;

public interface AdminService {

     void saveAdmin(AdminDto adminDto) ;

     AdminDto loginAdmin(AdminDto adminDto) ;

     List<UserDto> userDynamicSearch(UserCategoryDto userCategoryDto) ;

     List<OrderDto> orderDynamicSearch(OrderCategoryDto orderCategoryDto);

     long reportNumOfCustomerOrder(String email);

     long reportNumOfExpertDoneOrder(String email);
}

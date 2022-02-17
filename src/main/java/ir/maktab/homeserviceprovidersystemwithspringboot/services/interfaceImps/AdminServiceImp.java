package ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaceImps;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.dao.*;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.Admin;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.Order;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.roles.User;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.filterDto.OrderCategoryDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.filterDto.UserCategoryDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.mapper.OrderMapper;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.AdminDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.OrderDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.roles.UserDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.AdminNotFound;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.AdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImp implements AdminService {
    private final AdminDao adminDao;
    private final UserDao userDao;
    private final OrderDao orderDao ;
    private final ModelMapper modelMapper;
    private final OrderMapper orderMapper;

    @Override
    public void saveAdmin(AdminDto adminDto) {
            Admin admin =modelMapper.map(adminDto ,Admin.class);
            adminDao.save(admin);

    }

    @Override
    public AdminDto loginAdmin(AdminDto adminDto) {
        Admin admin = modelMapper.map(adminDto, Admin.class);
        Optional<Admin> found = adminDao.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
        if (found.isPresent()) {
            return modelMapper.map(found, AdminDto.class);
        } else
            throw new AdminNotFound();
    }

    @Override
    public List<UserDto> userDynamicSearch(UserCategoryDto userCategoryDto) {
        Sort sort = Sort.by("lastName").and(Sort.by("firstName"));
        Pageable pageable = PageRequest.of(userCategoryDto.getPageNumber(), userCategoryDto.getPageSize(), sort);


        Specification<User> specification = UserSpecifications.userFilter(userCategoryDto);

        return userDao
                .findAll(specification, pageable)
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> orderDynamicSearch(OrderCategoryDto orderCategoryDto) {
        Pageable pageable = PageRequest.of(orderCategoryDto.getPageNumber(),orderCategoryDto.getPageSize());

        Specification<Order> specification = OrderSpecifications.orderFilter(orderCategoryDto);

        return orderDao
                .findAll(specification,pageable)
                .stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public long reportNumOfCustomerOrder(String email) {
        return orderDao.countOfCustomerAskOrder(email);
    }

    @Override
    public long reportNumOfExpertDoneOrder(String email) {
        return orderDao.countOfExpertDoneOrder(email);
    }
}

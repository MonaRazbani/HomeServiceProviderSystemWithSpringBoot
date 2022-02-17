package ir.maktab.homeserviceprovidersystemwithspringboot.web.controller;


import ir.maktab.homeserviceprovidersystemwithspringboot.dto.filterDto.OrderCategoryDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.filterDto.UserCategoryDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.AdminDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.OrderDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.ServiceCategoryDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.SubServiceDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.AdminNotFound;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.BadFilterSearching;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.DuplicateSubService;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.AdminService;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.ServiceCategoryService;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminRestController {
    private final AdminService adminService;
    private final SubServiceService subServiceService;
    private final ServiceCategoryService serviceCategoryService;


    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AdminDto login(@RequestBody AdminDto adminDto) {
        return adminService.loginAdmin(adminDto);
    }
/*
    @PostMapping(value = "/UserDynamicSearch", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> searchUser(@RequestBody UserCategoryDto userCategoryDto) {
        return adminService.userDynamicSearch(userCategoryDto);
    }*/



    @PostMapping("/addSubService")
    public void addSubService(@RequestBody SubServiceDto subServiceDto) {
        subServiceService.saveSubService(subServiceDto);
    }

    @PostMapping(value = "/addServiceCategory", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addServiceCategory(@RequestBody ServiceCategoryDto serviceCategoryDto) {
        serviceCategoryService.saveServiceCategory(serviceCategoryDto);
    }

    @PostMapping(value = "/orderDynamicSearch", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDto> orderDynamicSearch(@RequestBody OrderCategoryDto orderCategoryDto) {
        return adminService.orderDynamicSearch(orderCategoryDto);
    }

    @PostMapping(value = "/customerReport",consumes = MediaType.APPLICATION_JSON_VALUE)
    public long customerReport (@RequestBody String email){
       return adminService.reportNumOfCustomerOrder(email);
    }

    @PostMapping(value = "/expertReport",consumes = MediaType.APPLICATION_JSON_VALUE)
    public long expertReport (@RequestBody String email){
       return adminService.reportNumOfExpertDoneOrder(email);
    }


    @ExceptionHandler(value = AdminNotFound.class)
    public ModelAndView loginExceptionHandler(AdminNotFound ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("adminDto", new AdminDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("admin/login", model);
    }

    @ExceptionHandler(value = BadFilterSearching.class)
    public ModelAndView loginExceptionHandler(BadFilterSearching ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("userCategoryDto", new UserCategoryDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("admin/searchUser", model);
    }

    @ExceptionHandler(value = DuplicateSubService.class)
    public ModelAndView signupExceptionHandler(DuplicateSubService ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("subServiceDto", new SubServiceDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("admin/addSubService", model);
    }


}


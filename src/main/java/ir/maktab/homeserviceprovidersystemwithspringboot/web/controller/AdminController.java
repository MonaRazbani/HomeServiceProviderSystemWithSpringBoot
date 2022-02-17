package ir.maktab.homeserviceprovidersystemwithspringboot.web.controller;

import ir.maktab.homeserviceprovidersystemwithspringboot.dto.filterDto.UserCategoryDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.AdminDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.ServiceCategoryDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.SubServiceDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.roles.UserDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.AccessDenied;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.BadFilterSearching;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.DuplicateSubService;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.AdminService;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.ServiceCategoryService;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"adminDto"})
@RequestMapping(value = "/admin")
public class AdminController {
    private final AdminService adminService;
    private final SubServiceService subServiceService;
    private final ServiceCategoryService serviceCategoryService;


    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("admin/login", "adminDto", new AdminDto());
    }

/*    @PostMapping("/login")
    public ModelAndView loginCustomer(@ModelAttribute("adminDto") @Validated AdminDto adminDto,HttpSession httpSession) {
        AdminDto loginAdmin = adminService.loginAdmin(adminDto);
        httpSession.setAttribute("adminDto",loginAdmin);

        return new ModelAndView("admin/dashboard");
    }*/

    @GetMapping("/dashboard")
    public String showDashboardPage (){
        return "/admin/dashboard";
    }

    @GetMapping("/searchUser")
    public ModelAndView showSearchUserPage(HttpSession session) {
        if (session.getAttribute("adminDto")==null)
            throw new AccessDenied();

        List<SubServiceDto> serviceServiceDtoAll = subServiceService.findAll();
        session.setAttribute("serviceServiceDtoAll", serviceServiceDtoAll);

        Map<String, Object> model = new HashMap<>();
        model.put("userCategoryDto", new UserCategoryDto());
        model.put("serviceServiceDtoAll", serviceServiceDtoAll);

        return new ModelAndView("admin/searchUser", model);
    }

    @PostMapping("/searchUserProcess")
    public ModelAndView searchUser(@ModelAttribute("userCategoryDto") UserCategoryDto userCategoryDto, ModelAndView modelAndView) {
        List<UserDto> userDtoList = adminService.userDynamicSearch(userCategoryDto);
        modelAndView.addObject("userDtoList", userDtoList);
        modelAndView.setViewName("admin/searchUser");
        return modelAndView;
    }

    @GetMapping(value = "/addSubService")
    public ModelAndView showAddSubServicePage() {
        List<String> serviceCategoryNameAll = serviceCategoryService.findAll().
                stream().
                map(ServiceCategoryDto::getName).
                collect(Collectors.toList());
        Map<String, Object> model = new HashMap<>();
        model.put("subServiceDto", new SubServiceDto());
        model.put("serviceCategoryNameAll", serviceCategoryNameAll);

        return new ModelAndView("/admin/addSubService", model);
    }

    @PostMapping("/addSubServiceProcess")
    public String addSubService(@ModelAttribute("subServiceDto") SubServiceDto subServiceDto) {
        subServiceService.saveSubService(subServiceDto);

        return "admin/dashboard";
    }

    @GetMapping("/addServiceCategory")
    public ModelAndView showAddServiceCategory() {
        return new ModelAndView("admin/addServiceCategory", "serviceCategoryDto", new ServiceCategoryDto());
    }

    @PostMapping("/addServiceCategoryProcess")
    public String addServiceCategory(@ModelAttribute("serviceCategoryDto") ServiceCategoryDto serviceCategoryDto) {
        serviceCategoryService.saveServiceCategory(serviceCategoryDto);
        return "admin/dashboard";
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

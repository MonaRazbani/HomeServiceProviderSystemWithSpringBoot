package ir.maktab.homeserviceprovidersystemwithspringboot.web.controller;

import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.OfferDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.OrderDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.ServiceCategoryDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.SubServiceDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.roles.ExpertDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.AccessDenied;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.DuplicateEmail;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.ExpertNotFound;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.*;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.validation.OnExpertSignup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/expert")

public class ExpertController {
    private final ExpertService expertService;
    private final ServiceCategoryService serviceCategoryService;
    private final SubServiceService subServiceService;
    private final OrderService orderService;
    private final OfferService offerService;


    @GetMapping(value = "/signup")
    public ModelAndView showSignupPage() {
        return new ModelAndView("expert/signup", "expertDto", new ExpertDto());
    }

    @PostMapping("/signup")
    public ModelAndView registerCustomer(@ModelAttribute("expertDto") @Validated(OnExpertSignup.class) ExpertDto expertDto,
                                         @RequestParam("image") MultipartFile image, ModelAndView modelAndView) throws IOException {

        expertService.saveExpert(expertDto, image);
        modelAndView.setViewName("expert/dashboard");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("expert/login", "expertDto", new ExpertDto());
    }

   /* @PostMapping("/login")
    public String loginCustomer(@ModelAttribute("ExpertDto") @Validated(OnExpertLogin.class) ExpertDto expertDto,
                                HttpSession httpSession) {
        ExpertDto expertDtoLogin = expertService.loginExpert(expertDto);
        httpSession.setAttribute("expertDto", expertDtoLogin);
        return "expert/dashboard";
    }*/

    @GetMapping("/dashboard")
    public String showDashboardPage() {

        return "expert/dashboard";
    }

    @GetMapping("/listOfServiceCategory")
    public ModelAndView listOfServiceCategoryPage() {

        List<String> categoryServiceAll = serviceCategoryService.findAll().
                stream().
                map(ServiceCategoryDto::getName).
                collect(Collectors.toList());

        return new ModelAndView("expert/selectServiceCategory", "categoryServiceAll", categoryServiceAll);
    }

    @GetMapping("/selectServiceCategory/{name}")
    public ModelAndView showSelectSubServicePage(HttpSession httpSession, @PathVariable String name) {

        List<String> subServiceDtoList = subServiceService.findSubServicesOfServiceCategory(name).
                stream().
                map(SubServiceDto::getName).
                collect(Collectors.toList());

        return new ModelAndView("expert/selectSubService", "subServiceDtoList", subServiceDtoList);
    }

    @PostMapping("/selectSubService")
    public ModelAndView selectSubServiceProcess(Principal principal,
                                                @RequestParam("subServiceName") String subServiceName) {

        expertService.addSubServiceToExpertSubServices(principal.getName(), subServiceName);

        return new ModelAndView("expert/listOfSubService", "orderDto", new OrderDto());

    }

    @GetMapping("/listOfOrders")
    public ModelAndView showListOfOrder(Principal principal) {

        List<OrderDto> orderDtoList =
                orderService.findOrderForExpertBasedOnSubService(principal.getName());

        Map<String, Object> model = new HashMap<>();
        model.put("orderDtoList", orderDtoList);
        model.put("OrderDto", new OfferDto());

        return new ModelAndView("/expert/selectOrder", model);
    }

    @GetMapping("/selectOrder/{identificationCode}")
    public ModelAndView showSubmitOrderPage(@PathVariable String identificationCode, HttpSession httpSession) {


        OrderDto orderDto = orderService.findOrderDtoByIdentificationCode(UUID.fromString(identificationCode));
        httpSession.setAttribute("orderDto", orderDto);


        Map<String, Object> model = new HashMap<>();
        model.put("offerDto", new OfferDto());

        return new ModelAndView("/offer/submitOffer", model);
    }


    @ExceptionHandler(value = ExpertNotFound.class)
    public ModelAndView loginExceptionHandler(ExpertNotFound ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("expertDto", new ExpertDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("expert/login", model);
    }

    @ExceptionHandler(value = DuplicateEmail.class)
    public ModelAndView signupExceptionHandler(DuplicateEmail ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("expertDto", new ExpertDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("expert/signup", model);
    }


}
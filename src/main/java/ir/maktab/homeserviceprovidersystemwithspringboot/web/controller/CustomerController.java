package ir.maktab.homeserviceprovidersystemwithspringboot.web.controller;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.Offer;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.Order;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.roles.User;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.enums.OrderStatus;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.OnRegistrationCompleteEventDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.OfferDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.OrderDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.roles.CustomerDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.DuplicateEmail;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.CustomerService;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.OfferService;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.OrderService;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.UserService;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.validation.OnCustomerSignup;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")

public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;
    private final OfferService offerService;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    @GetMapping(value = "/signup")
    public ModelAndView showSignupPage() {
        return new ModelAndView("customer/signup", "customerDto", new CustomerDto());
    }

    @PostMapping("/signup")
    public ModelAndView registerCustomer(@ModelAttribute("customerDto") @Validated(OnCustomerSignup.class) CustomerDto customerDto,
                                   HttpServletRequest request, Error error) {
            CustomerDto saveCustomer = customerService.saveCustomer(customerDto);
            User user = userService.findByEmail(customerDto.getEmail());
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEventDto(user,
                    request.getLocale(), appUrl));
        return new ModelAndView("successRegister", "customerDto", customerDto);
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("/customer/login", "customerDto", new CustomerDto());
    }

   /* @PostMapping("/login")
    public String loginCustomer(@ModelAttribute("customerDto") @Validated(OnCustomerLogin.class) CustomerDto customerDto,
                                HttpSession httpSession) {
        CustomerDto loginCustomer = customerService.loginCustomer(customerDto);
        httpSession.setAttribute("customerEmail", loginCustomer.getEmail());
        return "/customer/dashboard";
    }*/
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "/customer/dashboard" ;
    }

    @GetMapping("/listOfOrder")
    public ModelAndView showSelectOfOrderPage(Principal principal) {

        CustomerDto customerDto = customerService.findCustomerDtoByEmail(principal.getName());
        List<Order> customerOrders = orderService.findOrderByCustomer(customerDto);

        return new ModelAndView("/customer/selectOrder", "customerOrders", customerOrders);
    }

    @GetMapping("/selectOrder/{identificationCode}")
    public ModelAndView showOfferOfOrderPage(HttpSession httpSession,
                                             @PathVariable String identificationCode) {

        OrderDto orderDto = orderService.findOrderDtoByIdentificationCode(UUID.fromString(identificationCode));
        httpSession.setAttribute("orderDto", orderDto);

        if (orderDto.getStatus() == OrderStatus.WAITING_FOR_CHOOSING_EXPERT) {
            List<OfferDto> offerDtosOfOrder = offerService.findOfferDtosOfOrder(orderDto);

            Map<String, Object> model = new HashMap<>();
            model.put("orderDto", orderDto);
            model.put("offerDtosOfOrder", offerDtosOfOrder);

            return new ModelAndView("/customer/showOfferOfOrder", model);
        } else {
            OfferDto acceptedOfferOfOrder = offerService.findAcceptedOfferOfOrder(orderDto);
            httpSession.setAttribute("acceptedOfferOfOrder", acceptedOfferOfOrder);
            Map<String, Object> model = new HashMap<>();
            model.put("orderDto", orderDto);
            model.put("acceptedOfferOfOrder", acceptedOfferOfOrder);
            model.put("orderStatus", OrderStatus.DONE);

            return new ModelAndView("/order/showOrderMenuForCustomer", model);
        }
    }

    @GetMapping("/showOfferOfOrder/{identificationCode}")
    public String acceptOfferOfOrderPage(@PathVariable String identificationCode) throws ParseException {

        Offer offer = offerService.findOfferByIdentificationCode(UUID.fromString(identificationCode));
        offerService.acceptOfferForOrder(offer);

        return "/customer/showOfferOfOrder";
    }


    @ExceptionHandler(value = DuplicateEmail.class)
    public ModelAndView signupExceptionHandler(DuplicateEmail ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("customerDto", new CustomerDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("/customer/signup", model);
    }
}
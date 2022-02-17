package ir.maktab.homeserviceprovidersystemwithspringboot.web.controller;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.enums.OfferStatus;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.OfferDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.OrderDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.roles.ExpertDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.AccessDenied;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.ExpertService;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.OfferService;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/offer")
@RequiredArgsConstructor
public class OfferController {
    private final OfferService offerService;
    private final OrderService orderService;
    private final ExpertService expertService;

    @PostMapping("submitOffer")
    public ModelAndView submitOffer(@SessionAttribute("orderDto") OrderDto orderDto,
                                    @ModelAttribute("offerDto") OfferDto offerDto,
                                    Principal principal) throws ParseException {
        ExpertDto expertDto = expertService.findExpertDtoByEmail(principal.getName());
        offerDto.setExpert(expertDto);
        offerDto.setOrder(orderDto);

        offerService.saveOffer(offerDto);

        return new ModelAndView("/offer/expertOffers");
    }

    @GetMapping("/expertOffers")
    public ModelAndView showExpertOfferPage(Principal principal) {

        List<OfferDto> expertOffers = offerService.findExpertOffer(principal.getName());

        return new ModelAndView("offer/showExpertOffers", "expertOffers", expertOffers);
    }

    @GetMapping("/expertOffers/{identificationCode}")
    public String selectOfferFromShowExpertOfferPage(@PathVariable String identificationCode,
                                                     HttpSession httpSession) {

        OfferDto offerDto = offerService.findOfferDtoByIdentificationCode(UUID.fromString(identificationCode));
        httpSession.setAttribute("offerDto", offerDto);

        return "offer/showOfferMenuForExpert";
    }

    @GetMapping("/editOfferStartDate")
    public String showEditOfferStartDatePage(@SessionAttribute("offerDto") OfferDto offerDto) {
        if (offerDto == null)
            throw new AccessDenied();

        return "/offer/editOfferStartDate";
    }

    @PostMapping("/editOfferStartDate")
    public String editOfferStartDate(@SessionAttribute("offerDto") OfferDto offerDto,
                                     @RequestParam(value = "startDate", required = false) @NotNull @DateTimeFormat(pattern = "hh:mm") String startDate) {

        if (offerDto == null)
            throw new AccessDenied();

        try {
            offerDto = offerService.editStartDateOffer(offerDto, startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "offer/showOfferMenuForExpert";
    }


    @GetMapping("/editOfferSuggestedPrice")
    public String showEditOfferSuggestedPricePage(@SessionAttribute("offerDto") OfferDto offerDto) {
        if (offerDto == null)
            throw new AccessDenied();
        return "/offer/editOfferSuggestedPrice";
    }

    @PostMapping("/editOfferSuggestedPrice")
    public String editOfferSuggestedPrice(@SessionAttribute("offerDto") OfferDto offerDto,
                                          @RequestParam(value = "suggestedPrice", required = false) @NotNull @NumberFormat String suggestedPrice) {

        if (offerDto == null)
            throw new AccessDenied();

        try {
            offerDto = offerService.editOfferSuggestedPrice(offerDto, suggestedPrice);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "offer/showOfferMenuForExpert";
    }

    @GetMapping("/editOfferSuggestedDurationOfService")
    public String showEditOfferSuggestedDurationOfServicePage(@SessionAttribute("offerDto") OfferDto offerDto) {
        if (offerDto == null)
            throw new AccessDenied();

        return "/offer/editOfferSuggestedDurationOfService";
    }

    @PostMapping("/editOfferSuggestedDurationOfService")
    public String editOfferSuggestedDurationOfService(@SessionAttribute("offerDto") OfferDto offerDto,
                                                      @RequestParam(value = "suggestedDurationOfService", required = false)
                                                      @NotNull @NumberFormat String suggestedDurationOfService) {

        if (offerDto == null)
            throw new AccessDenied();

        try {
            offerDto = offerService.editSuggestedDurationOfService(offerDto, suggestedDurationOfService);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "offer/showOfferMenuForExpert";
    }

    @GetMapping("/changeOrderStatus")
    public String ShowChangeOfferStatus(@SessionAttribute("offerDto") OfferDto offerDto) {

        if (offerDto == null && offerDto.getStatus() != OfferStatus.ACCEPT)
            throw new AccessDenied();

        return "/offer/changeOrderStatus";
    }

    @GetMapping("/changeOrderStatus/{status}")
    public String changeOfferStatus(@SessionAttribute("offerDto") OfferDto offerDto,
                                    @PathVariable String status,
                                    HttpSession httpSession) {
        if (offerDto == null && offerDto.getStatus() != OfferStatus.ACCEPT)
            throw new AccessDenied();

        try {
            offerService.changeOrderStatus(offerDto, status);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        httpSession.removeAttribute("offerDto");
        return "/offer/expertOffers";
    }

}


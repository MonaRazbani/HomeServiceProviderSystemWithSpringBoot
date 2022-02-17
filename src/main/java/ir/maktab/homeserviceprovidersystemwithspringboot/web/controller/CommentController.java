package ir.maktab.homeserviceprovidersystemwithspringboot.web.controller;

import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.CommentDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.OrderDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.roles.CustomerDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.AccessDenied;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/submitComment")
    public ModelAndView showSubmitCommentPage(@SessionAttribute("customerDto")CustomerDto customerDto,
                                              @SessionAttribute("orderDto") OrderDto orderDto){
        if(customerDto==null && orderDto==null)
            throw new AccessDenied();

        return new ModelAndView("order/submitComment","commentDto",new CommentDto());
    }
    @PostMapping("/submitComment")
    public String submitCommentProcess (@SessionAttribute("customerDto")CustomerDto customerDto,
                                        @SessionAttribute("orderDto") OrderDto orderDto,
                                        @ModelAttribute("commentDto")CommentDto commentDto){

        if(customerDto==null && orderDto==null)
            throw new AccessDenied();


        return "/customer/dashboard";
    }
}

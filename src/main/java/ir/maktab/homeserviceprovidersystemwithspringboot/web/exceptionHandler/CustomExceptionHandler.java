package ir.maktab.homeserviceprovidersystemwithspringboot.web.exceptionHandler;

import ir.maktab.homeserviceprovidersystemwithspringboot.configuration.LastViewInterceptor;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.AccessDenied;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, ex.getBindingResult().getModel());
    }
    @ExceptionHandler(value = AccessDenied.class)
    public ModelAndView accessDenied (AccessDenied ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, "error", ex.getMessage());
    }

}

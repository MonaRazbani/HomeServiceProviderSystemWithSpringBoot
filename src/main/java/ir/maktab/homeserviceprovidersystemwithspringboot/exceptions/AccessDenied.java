package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class AccessDenied extends RuntimeException{
    public AccessDenied() {
        super("accessDenied");
    }
}

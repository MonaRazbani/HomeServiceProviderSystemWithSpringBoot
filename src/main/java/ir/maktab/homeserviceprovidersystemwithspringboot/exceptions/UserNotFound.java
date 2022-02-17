package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class UserNotFound extends RuntimeException{
    public UserNotFound() {
        super("UserNotFound");
    }
}

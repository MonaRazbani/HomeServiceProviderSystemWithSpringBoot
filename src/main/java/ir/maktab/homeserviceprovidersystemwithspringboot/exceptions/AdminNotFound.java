package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class AdminNotFound extends RuntimeException{
    public AdminNotFound() {
        super("Admin Not Found");
    }
}

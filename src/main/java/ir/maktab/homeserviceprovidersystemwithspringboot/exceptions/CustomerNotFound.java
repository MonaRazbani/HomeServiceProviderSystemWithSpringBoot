package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class CustomerNotFound extends RuntimeException{
    public CustomerNotFound() {
        super("customer not found");
    }
}

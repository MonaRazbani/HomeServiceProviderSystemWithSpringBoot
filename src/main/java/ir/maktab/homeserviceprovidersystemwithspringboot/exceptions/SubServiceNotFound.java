package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class SubServiceNotFound extends RuntimeException {
    public SubServiceNotFound() {
        super("SubService Not Found");
    }
}

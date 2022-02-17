package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class AddressNotFound extends RuntimeException{
    public AddressNotFound() {
        super("Address Not Found");
    }
}

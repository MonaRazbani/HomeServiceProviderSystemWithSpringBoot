package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class InvalidEmail extends RuntimeException {
    public InvalidEmail() {
        super("invalid email");
    }
}

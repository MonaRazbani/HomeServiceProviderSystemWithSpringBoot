package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class InvalidName extends RuntimeException{
    public InvalidName() {
        super("invalid firs name or last name");
    }
}

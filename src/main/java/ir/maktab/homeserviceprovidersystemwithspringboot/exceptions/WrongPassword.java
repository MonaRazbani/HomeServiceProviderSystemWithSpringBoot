package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class WrongPassword extends RuntimeException{
    public WrongPassword() {
        super("wrong Password");
    }
}

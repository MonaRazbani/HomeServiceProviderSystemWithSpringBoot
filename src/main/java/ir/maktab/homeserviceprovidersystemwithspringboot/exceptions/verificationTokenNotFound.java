package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class verificationTokenNotFound extends RuntimeException{
    public verificationTokenNotFound() {
        super("verificationToken Not Found");
    }
}

package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class EditionDenied extends RuntimeException{
    public EditionDenied() {
        super("edition denied");
    }
}

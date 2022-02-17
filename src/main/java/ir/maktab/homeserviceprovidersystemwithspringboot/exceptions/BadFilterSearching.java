package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class BadFilterSearching extends RuntimeException{
    public BadFilterSearching() {
        super("search SubService is only for expert");
    }
}

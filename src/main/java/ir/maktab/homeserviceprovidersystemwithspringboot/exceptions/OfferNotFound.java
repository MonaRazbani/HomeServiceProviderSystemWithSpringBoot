package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class OfferNotFound extends RuntimeException{
    public OfferNotFound() {
        super("No Offer Found");
    }
}

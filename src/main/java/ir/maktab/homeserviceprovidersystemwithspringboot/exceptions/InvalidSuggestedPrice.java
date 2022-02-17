package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class InvalidSuggestedPrice extends RuntimeException {
    public InvalidSuggestedPrice() {
        super("invalid SuggestedPrice , SuggestedPrice must be more than service base cost " );
    }
}

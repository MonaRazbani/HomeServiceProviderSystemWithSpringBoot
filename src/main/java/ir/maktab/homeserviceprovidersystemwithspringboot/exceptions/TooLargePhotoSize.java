package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class TooLargePhotoSize extends RuntimeException {
    public TooLargePhotoSize() {
        super("size of photo most less than 300 kB");
    }
}

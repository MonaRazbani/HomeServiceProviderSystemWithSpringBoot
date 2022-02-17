package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class DuplicateEmail extends RuntimeException {
    public DuplicateEmail() {
        super("This email has been used before");
    }
}

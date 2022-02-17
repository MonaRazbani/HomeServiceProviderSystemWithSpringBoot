package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class DuplicateSubService extends RuntimeException{
    public DuplicateSubService() {
        super("this subService already exists");
    }
}

package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class DuplicateServiceCategory extends RuntimeException{
    public DuplicateServiceCategory() {
        super("this serviceCategory already exists");
    }
}

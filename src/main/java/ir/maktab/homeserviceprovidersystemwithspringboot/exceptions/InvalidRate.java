package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class InvalidRate extends RuntimeException{
    public InvalidRate() {
        super(" invalid rate , rate must between 5-0");
    }
}

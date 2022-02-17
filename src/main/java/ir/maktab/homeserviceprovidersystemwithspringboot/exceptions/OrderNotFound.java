package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class OrderNotFound extends RuntimeException{
    public OrderNotFound() {
        super("no order found ");
    }
}

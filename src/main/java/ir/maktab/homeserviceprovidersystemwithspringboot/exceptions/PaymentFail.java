package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class PaymentFail extends RuntimeException{
    public PaymentFail() {
        super("Payment Fail");
    }
}

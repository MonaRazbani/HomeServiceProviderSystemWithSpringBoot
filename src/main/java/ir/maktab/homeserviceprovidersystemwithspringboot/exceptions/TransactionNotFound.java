package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class TransactionNotFound extends RuntimeException{
    public TransactionNotFound() {
        super("Transaction Not Found");
    }
}

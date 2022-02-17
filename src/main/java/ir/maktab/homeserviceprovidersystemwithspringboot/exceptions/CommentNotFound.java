package ir.maktab.homeserviceprovidersystemwithspringboot.exceptions;

public class CommentNotFound extends RuntimeException{
    public CommentNotFound() {
        super("Comment Not Found");
    }
}

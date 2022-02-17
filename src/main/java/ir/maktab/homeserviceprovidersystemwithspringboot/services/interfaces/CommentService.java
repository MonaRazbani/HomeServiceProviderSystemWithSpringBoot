package ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.Comment;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.CommentDto;

import java.util.UUID;

public interface CommentService {
     Comment saveComment(CommentDto commentDto);

     void updateComment(CommentDto commentDto) ;

     void deleteComment(CommentDto commentDto) ;

     Comment findCommentById(long id) ;

     Comment findCommentByIdentificationCode(UUID identificationCode) ;

     long findCommentId(UUID identificationCode);
}

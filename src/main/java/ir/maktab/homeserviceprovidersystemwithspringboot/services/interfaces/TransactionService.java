package ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces;


import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.Order;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.TransactionDto;

import java.util.UUID;

public interface TransactionService {

    TransactionDto save (TransactionDto transactionDto, Order order);

    TransactionDto findTransactionByIdentificationCode(UUID identificationCode );


}

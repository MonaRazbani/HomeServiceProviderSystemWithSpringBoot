package ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaceImps;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.dao.TransactionDao;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.Order;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.Transaction;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.mapper.TransactionMapper;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.TransactionDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.TransactionNotFound;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImp implements TransactionService {
    private final TransactionDao transactionDao;
    private final TransactionMapper transactionMapper;


    @Override
    public TransactionDto save(TransactionDto transactionDto, Order order) {
        Transaction transaction = transactionMapper.toTransaction(transactionDto);
        transaction.setIdentificationCode(UUID.randomUUID());
        transaction.setOrder(order);
        Transaction save = transactionDao.save(transaction);
        return transactionMapper.toTransactionDto(save);

    }

    @Override
    public TransactionDto findTransactionByIdentificationCode(UUID identificationCode) {
        Optional<Transaction> transaction = transactionDao.findByIdentificationCode(identificationCode);
        if(transaction.isEmpty())
            throw new TransactionNotFound();
        return transactionMapper.toTransactionDto(transaction.get());
    }
}

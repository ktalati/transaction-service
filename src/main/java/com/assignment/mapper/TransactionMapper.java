package com.assignment.mapper;

import com.assignment.entity.Transaction;
import com.assignment.enums.TransactionType;
import com.assignment.model.TransactionDetail;
import com.assignment.model.TransactionDetailInner;
import com.assignment.model.TransactionResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {

    TransactionMapper TRANSACTION_MAPPER = Mappers.getMapper(TransactionMapper.class);

    TransactionResponse map(@MappingTarget TransactionResponse transactionResponse, Transaction transaction);

    @AfterMapping
    default void processTransactions(@MappingTarget TransactionResponse transactionResponse, Transaction transaction) {
        BigDecimal balance = transactionResponse.getBalance()==null ? new BigDecimal(0) : transactionResponse.getBalance();
        BigDecimal amount = transaction.getAmount();
        if(transaction.getType().equalsIgnoreCase(TransactionType.CREDIT.name())){
            balance = balance.add(amount);
        }else{
            balance = balance.subtract(amount);
        }
        transactionResponse.setBalance(balance);
        if(CollectionUtils.isEmpty(transactionResponse.getTransactions())){
            transactionResponse.setTransactions(new TransactionDetail());
        }
        transactionResponse.getTransactions().add(new TransactionDetailInner()
                .transactionId(transaction.getId())
                .amount(transaction.getAmount())
                .createdAt(transaction.getCreatedAt())
                .type(transaction.getType()));
    }
}
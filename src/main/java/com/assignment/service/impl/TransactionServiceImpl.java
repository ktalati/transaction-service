package com.assignment.service.impl;

import com.assignment.enums.TransactionType;
import com.assignment.entity.Transaction;
import com.assignment.exception.APIException;
import com.assignment.mapper.TransactionMapper;
import com.assignment.model.TransactionResponse;
import com.assignment.repository.TransactionRepository;
import com.assignment.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public void createTransaction(Integer accountId, BigDecimal amount) {
        try {
            log.trace("Initiating Transaction for account {} with amount {}", accountId, amount);
            Transaction transaction = new Transaction();
            transaction.setAccountId(accountId);
            transaction.setAmount(amount);
            transaction.setType(TransactionType.CREDIT.name());
            transactionRepository.save(transaction);
            log.trace("Transaction for account {} with amount {} completed", accountId, amount);
        } catch (Exception e){
            log.error("Error while creating transaction for account {} {}", accountId, e.getMessage());
            throw new APIException(e.getMessage());
        }
    }

    @Override
    public TransactionResponse fetchTransactionByAccountId(Integer accountId) {
        log.trace("Fetching Transactions for AccountId {}", accountId);
        Optional<List<Transaction>> transactions = transactionRepository.findByAccountId(accountId);
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setAccountId(accountId);
        transactions.ifPresent(transactionList -> transactionList.forEach(transaction -> TransactionMapper.TRANSACTION_MAPPER.map(transactionResponse, transaction)));
        log.trace("Fetching Transactions completed for AccountId {}", accountId);
        return transactionResponse;
    }
}
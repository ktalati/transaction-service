package com.assignment.service.impl;

import com.assignment.Constants;
import com.assignment.entity.Transaction;
import com.assignment.model.TransactionResponse;
import com.assignment.repository.TransactionRepository;
import com.assignment.util.TransactionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;
    @Mock
    private TransactionRepository transactionRepository;

    @Test
    void test_createTransaction_when_valid_request_provided() throws Exception {
        Transaction mockedTransactionResponse = TransactionUtil.prepareTransaction();
        Mockito.when(transactionRepository.save(ArgumentMatchers.any())).thenReturn(mockedTransactionResponse);
        transactionService.createTransaction(Constants.CURRENT_ACCOUNT_ID, Constants.AMOUNT);
        Mockito.verify(transactionRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test
    void test_getTransactionByAccountId_when_valid_request_provided() throws Exception {
        List<Transaction> mockedTransactions = TransactionUtil.prepareTransactions();
        Mockito.when(transactionRepository.findByAccountId(ArgumentMatchers.any())).thenReturn(Optional.of(mockedTransactions));
        TransactionResponse actualTransactions = transactionService.fetchTransactionByAccountId(Constants.CURRENT_ACCOUNT_ID);
        Assertions.assertEquals(mockedTransactions.size(), actualTransactions.getTransactions().size());
    }
}
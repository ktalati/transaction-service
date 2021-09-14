package com.assignment.service;

import com.assignment.model.TransactionResponse;

import java.math.BigDecimal;

public interface TransactionService {

    void createTransaction(Integer accountId, BigDecimal amount);

    TransactionResponse fetchTransactionByAccountId(Integer accountId);
}

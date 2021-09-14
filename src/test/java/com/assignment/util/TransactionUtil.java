package com.assignment.util;

import com.assignment.Constants;
import com.assignment.entity.Transaction;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionUtil {

    public static Transaction prepareTransaction(){
        Transaction transaction = new Transaction();
        transaction.setType("CREDIT");
        transaction.setAmount(Constants.AMOUNT);
        transaction.setAccountId(Constants.CURRENT_ACCOUNT_ID);
        transaction.setCreatedAt(OffsetDateTime.now());
        return transaction;
    }

    public static List<Transaction> prepareTransactions(){
        List<Transaction> transactions = new ArrayList<>();

        Transaction firstTransaction = new Transaction();
        firstTransaction.setType("CREDIT");
        firstTransaction.setAmount(Constants.AMOUNT);
        firstTransaction.setAccountId(Constants.CURRENT_ACCOUNT_ID);
        firstTransaction.setCreatedAt(OffsetDateTime.now());

        transactions.add(firstTransaction);

        Transaction secondTransaction = new Transaction();
        secondTransaction.setType("CREDIT");
        secondTransaction.setAmount(Constants.AMOUNT);
        secondTransaction.setAccountId(Constants.CURRENT_ACCOUNT_ID);
        secondTransaction.setCreatedAt(OffsetDateTime.now());

        transactions.add(secondTransaction);

        return transactions;
    }
}

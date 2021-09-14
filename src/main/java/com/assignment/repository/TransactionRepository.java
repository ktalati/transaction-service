package com.assignment.repository;

import com.assignment.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    Optional<List<Transaction>> findByAccountId(Integer accountId);

    void deleteByIdIn(List<Integer> transactionIds);
}

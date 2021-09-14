package com.assignment.controller;

import com.assignment.exception.APIException;
import com.assignment.model.CreateTransactionResponse;
import com.assignment.model.TransactionResponse;
import com.assignment.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<CreateTransactionResponse> createTransaction(@RequestParam Integer accountId, @RequestParam BigDecimal amount) throws APIException {
        try{
            transactionService.createTransaction(accountId, amount);
            return new ResponseEntity<>(new CreateTransactionResponse().message("Transaction created successfully."),HttpStatus.OK);
        }catch (Exception e){
            log.error("Error while creating transaction for accountId {} {}", accountId, e.getMessage());
            throw new APIException(e.getMessage());
        }
    }

    @GetMapping("/fetchTransactionByAccount")
    public ResponseEntity<TransactionResponse> fetchTransactionByAccountId(@RequestParam Integer accountId) {
        TransactionResponse transactionResponse = transactionService.fetchTransactionByAccountId(accountId);
        return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
    }
}

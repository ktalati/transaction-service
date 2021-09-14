package com.assignment.controller;

import com.assignment.Constants;
import com.assignment.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private TransactionService transactionService;

    private static final String CREATE_TRANSACTION_ENDPOINT = "/transaction/create";
    private static final String FETCH_TRANSACTION_BY_ACCOUNT_ENDPOINT = "/transaction/fetchTransactionByAccount";

    @Test
    void test_createTransaction_when_valid_request_provided() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(CREATE_TRANSACTION_ENDPOINT)
                        .param("accountId", Constants.CURRENT_ACCOUNT_ID.toString())
                        .param("amount", Constants.AMOUNT.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Mockito.verify(transactionService, Mockito.times(1))
                .createTransaction(Constants.CURRENT_ACCOUNT_ID, Constants.AMOUNT);
    }

    @Test
    void test_fetchTransactionByAccount_when_valid_request_provided() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(FETCH_TRANSACTION_BY_ACCOUNT_ENDPOINT)
                        .param("accountId", Constants.CURRENT_ACCOUNT_ID.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Mockito.verify(transactionService, Mockito.times(1))
                .fetchTransactionByAccountId(Constants.CURRENT_ACCOUNT_ID);
    }
}
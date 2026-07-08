package com.mihaela.orderplatform.transaction_service.service;

import com.mihaela.orderplatform.transaction_service.dto.CustomerTransactionDto;
import com.mihaela.orderplatform.transaction_service.mapper.CustomerTransactionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final CustomerTransactionMapper transactionMapper;

    public void processTransaction(JsonNode customerOrder) {
        log.debug("Transaction process begin");

        CustomerTransactionDto noFeeTransaction = transactionMapper.fromDebezium(customerOrder);
        //TODO -> add fees -> directly fetch fee table -> go over all the fees added to that transaction_id -> set to the CustomerTransactionDto + save to DB
    }


}

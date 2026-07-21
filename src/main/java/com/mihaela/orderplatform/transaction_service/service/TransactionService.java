package com.mihaela.orderplatform.transaction_service.service;

import com.mihaela.orderplatform.transaction_service.domain.CustomerTransaction;
import com.mihaela.orderplatform.transaction_service.enums.TransactionStatus;
import com.mihaela.orderplatform.transaction_service.mapper.CustomerTransactionMapper;
import com.mihaela.orderplatform.transaction_service.repository.CustomerTransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.JsonNode;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final CustomerTransactionMapper transactionMapper;
    private final FeeCalculationService feeCalculationService;
    private final CustomerTransactionRepository repository;

    @Transactional
    public void processTransaction(JsonNode customerOrder) {
        log.debug("Processing transaction...");

        CustomerTransaction transaction = transactionMapper.fromDebezium(customerOrder);

        transaction.setStatus(TransactionStatus.PENDING_PAYMENT);
        feeCalculationService.enrichTransaction(transaction);
        saveTransaction(transaction);

        log.trace("Transaction {} saved with {} fees", transaction.getOrderId(), transaction.getFees().size());
    }

    private void saveTransaction(CustomerTransaction transaction) {
        repository.save(transaction);
    }
}

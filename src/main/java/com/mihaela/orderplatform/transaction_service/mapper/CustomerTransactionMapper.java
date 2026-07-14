package com.mihaela.orderplatform.transaction_service.mapper;

import com.mihaela.orderplatform.transaction_service.domain.CustomerTransaction;
import com.mihaela.orderplatform.transaction_service.enums.Currency;
import com.mihaela.orderplatform.transaction_service.enums.TransactionStatus;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;

import java.math.BigDecimal;

@Component
public class CustomerTransactionMapper {

    public CustomerTransaction fromDebezium(JsonNode after) {
        CustomerTransaction transaction = new CustomerTransaction();

        transaction.setCustomerId(after.get("customer_id").asText());
        transaction.setOrderId(after.get("id").asLong());
        transaction.setAmount(new BigDecimal(after.get("amount").asText()));
        transaction.setCurrency(Currency.valueOf(after.get("currency").asText()));
        transaction.setStatus(TransactionStatus.valueOf(after.get("status").asText()));

        return transaction;
    }
}

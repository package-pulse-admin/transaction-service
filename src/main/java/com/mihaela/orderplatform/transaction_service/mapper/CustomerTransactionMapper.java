package com.mihaela.orderplatform.transaction_service.mapper;

import com.mihaela.orderplatform.transaction_service.domain.CustomerTransaction;
import com.mihaela.orderplatform.transaction_service.dto.CustomerTransactionDto;
import com.mihaela.orderplatform.transaction_service.enums.Currency;
import com.mihaela.orderplatform.transaction_service.enums.TransactionStatus;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;

import java.math.BigDecimal;

@Component
public class CustomerTransactionMapper {


    //TODO - create separate class for debezium fields
    public CustomerTransactionDto fromDebezium(JsonNode after) {
        return CustomerTransactionDto.builder()
                .customerId(after.get("customer_id").asText())
                .orderId(after.get("id").asLong())
                .amount(new BigDecimal(after.get("amount").asText()))
                .currency(Currency.valueOf(after.get("currency").asText()))
                .status(TransactionStatus.valueOf(after.get("status").asText()))
                .build();
    }

    public CustomerTransactionDto toDto(CustomerTransaction entity) {
        return CustomerTransactionDto.builder()
                .id(entity.getId())
                .customerId(entity.getCustomerId())
                .amount(entity.getAmount())
                .currency(entity.getCurrency())
                .status(entity.getStatus())
                .totalAmount(entity.getTotalAmount())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                //fees to me mapped
                .build();
    }

    public CustomerTransaction toEntity(CustomerTransactionDto dto) {
        CustomerTransaction entity = new CustomerTransaction();
        entity.setId(dto.getId());
        entity.setCustomerId(dto.getCustomerId());
        entity.setAmount(dto.getAmount());
        entity.setCurrency(dto.getCurrency());
        entity.setStatus(dto.getStatus());
        entity.setTotalAmount(dto.getTotalAmount());
        // fees to be mapped
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        return entity;
    }
}

package com.mihaela.orderplatform.transaction_service.mapper;

import com.mihaela.orderplatform.transaction_service.domain.CustomerTransaction;
import com.mihaela.orderplatform.transaction_service.dto.CustomerTransactionDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerTransactionMapper {

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

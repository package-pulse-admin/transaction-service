package com.mihaela.orderplatform.transaction_service.dto;

import com.mihaela.orderplatform.transaction_service.enums.Currency;
import com.mihaela.orderplatform.transaction_service.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTransactionDto {

    private Long id;
    private String customerId;
    private Long orderId;
    private BigDecimal amount;
    private Currency currency;
    private TransactionStatus status;
    private BigDecimal totalAmount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<TransactionFeeDto> fees;
}

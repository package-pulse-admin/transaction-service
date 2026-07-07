package com.mihaela.orderplatform.transaction_service.dto;

import com.mihaela.orderplatform.transaction_service.enums.FeeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionFeeDto {

    private Long id;
    private FeeType feeType;
    private BigDecimal amount;
    private String description;
    private LocalDateTime createdAt;
}

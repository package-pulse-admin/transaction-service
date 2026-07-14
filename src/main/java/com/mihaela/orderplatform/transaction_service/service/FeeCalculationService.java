package com.mihaela.orderplatform.transaction_service.service;

import com.mihaela.orderplatform.transaction_service.domain.CustomerTransaction;
import com.mihaela.orderplatform.transaction_service.domain.TransactionFee;
import com.mihaela.orderplatform.transaction_service.enums.Currency;
import com.mihaela.orderplatform.transaction_service.enums.FeeType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeeCalculationService {

    public List<TransactionFee> calculateFees(CustomerTransaction transaction) {

        List<TransactionFee> fees = new ArrayList<>();
        fees.add(createFee(transaction, FeeType.PROCESSING, BigDecimal.valueOf(2.50), "Processing fee"));

        if (transaction.getAmount().compareTo(BigDecimal.valueOf(100)) > 0) {
            BigDecimal serviceFee = transaction.getAmount().multiply(BigDecimal.valueOf(0.01));
            fees.add(createFee(transaction, FeeType.SERVICE, serviceFee, "1% service fee"));
        }

        if (transaction.getCurrency() != Currency.EUR) {
            fees.add(createFee(transaction, FeeType.FX, BigDecimal.valueOf(0.75), "Foreign currency fee"));
        }

        if (transaction.getAmount().compareTo(BigDecimal.valueOf(500)) > 0) {
            fees.add(createFee(transaction, FeeType.TAX, BigDecimal.valueOf(15), "High value transaction"));
        }

        return fees;
    }

    public void enrichTransaction(CustomerTransaction transaction) {
        List<TransactionFee> fees = calculateFees(transaction);

        transaction.setFees(fees);
        transaction.setTotalAmount(calculateTotalAmount(transaction, fees));
    }

    private BigDecimal calculateTotalAmount(CustomerTransaction transaction, List<TransactionFee> fees) {

        BigDecimal totalFees = fees.stream()
                .map(TransactionFee::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return transaction.getAmount().add(totalFees);
    }

    private TransactionFee createFee(CustomerTransaction transaction, FeeType type, BigDecimal amount, String description) {
        TransactionFee fee = new TransactionFee();
        fee.setTransaction(transaction);
        fee.setFeeType(type);
        fee.setAmount(amount);
        fee.setDescription(description);
        return fee;
    }
}

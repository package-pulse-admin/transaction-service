package com.mihaela.orderplatform.transaction_service.repository;

import com.mihaela.orderplatform.transaction_service.domain.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long> {

    Optional<CustomerTransaction> findByOrderId(Long orderId);

    List<CustomerTransaction> findByCustomerId(String customerId);
}

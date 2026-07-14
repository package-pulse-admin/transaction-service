package com.mihaela.orderplatform.transaction_service.kafka;

import com.mihaela.orderplatform.transaction_service.debezium.DebeziumMessage;
import com.mihaela.orderplatform.transaction_service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCdcListener {

    private final ObjectMapper objectMapper;
    private final TransactionService transactionService;

    @KafkaListener(
            topics = "order-platform.order_platform.customer_orders",
            groupId = "transaction-service"
    )
    public void listen(String message) {
        log.debug("Received cdc event");
        DebeziumMessage event = objectMapper.readValue(message, DebeziumMessage.class);

        if (!"c".equals(event.getPayload().getOp())) {
            return;
        }

        transactionService.processTransaction(event.getPayload().getAfter());
    }
}

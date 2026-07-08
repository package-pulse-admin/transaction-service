package com.mihaela.orderplatform.transaction_service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCdcListener {

    @KafkaListener(
            topics = "order-platform.order_platform.customer_orders",
            groupId = "transaction-service"
    )
    public void listen(String message) {
        log.debug("Received cdc event");
        System.out.println("MESSAGE ONE: " + message);
        //check for only 'c' events -> call service for business logic
    }
}

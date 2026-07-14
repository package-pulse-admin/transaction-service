package com.mihaela.orderplatform.transaction_service.debezium;

import lombok.Data;
import tools.jackson.databind.JsonNode;

@Data
public class DebeziumMessage {

    private Payload payload;

    @Data
    public static class Payload {

        private JsonNode after;
        private String op;
    }
}

package com.example.projetokafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public class FraudDetectorService {
    public static String ECOMMERCE_NEW_ORDER = "ECOMMERCE_NEW_ORDER";

    public static void main(String[] args) {
        var fraudDetectorService = new FraudDetectorService();
        KafkaService kafkaService = new KafkaService<>(
                FraudDetectorService.class.getSimpleName(),
                ECOMMERCE_NEW_ORDER,
                fraudDetectorService::parse,
                NewOrder.class,
                Map.of());
        kafkaService.run();
    }

    public void parse(ConsumerRecord<String, NewOrder> record){
        System.out.println("-------------------------------------------");
        System.out.println(record.key() + " | " + record.value() + " | " + record.offset() + " | " + record.timestamp());
        System.out.println("-------------------------------------------");
    }
}

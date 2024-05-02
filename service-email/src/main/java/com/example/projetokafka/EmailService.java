package com.example.projetokafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public class EmailService {

    public static String ECOMMERCE_SEND_EMAIL = "ECOMMERCE_SEND_EMAIL";

    public static void main(String[] args) {
        var emailService = new EmailService();
        KafkaService kafkaService = new KafkaService<>(
                EmailService.class.getSimpleName(),
                ECOMMERCE_SEND_EMAIL,
                emailService::parse,
                Email.class,
                Map.of());
        kafkaService.run();
    }

    public void parse(ConsumerRecord<String, Email> record){
        System.out.println("-------------------------------------------");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        System.out.println("-------------------------------------------");
    }

}

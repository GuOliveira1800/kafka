package com.example.projetokafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;

public class FraudDetectorService {

    public static void main(String[] args) {
        var fraudDetectorService = new FraudDetectorService();
        KafkaService kafkaService = new KafkaService(FraudDetectorService.class.getSimpleName(),"ECOMMERCE_NEW_ORDER", fraudDetectorService::parse);
        kafkaService.run();
    }

    public void parse(ConsumerRecord<String, String> record){
        System.out.println("-------------------------------------------");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        System.out.println("-------------------------------------------");
    }
}

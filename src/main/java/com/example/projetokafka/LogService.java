package com.example.projetokafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.regex.Pattern;

public class LogService {

    public static void main(String[] args) {
        var logService = new LogService();
        KafkaService kafkaService = new KafkaService(LogService.class.getSimpleName(),Pattern.compile("ECOMMERCE.*"), logService::parse);
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

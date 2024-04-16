package com.example.projetokafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class FraudDetectorService {

    public static void main(String[] args) {

        var consumer = new KafkaConsumer<String, String>(properties());
        while (true) {
            consumer.subscribe(Collections.singletonList("ECOMMERCE_NEW_ORDER"));
            var records = consumer.poll(Duration.ofMillis(5000));
            if (records.isEmpty()) {
                System.out.println("Sem registro encontrados");
            }
            for (var record : records) {
                System.out.println("-------------------------------------------");
                System.out.println(record.key());
                System.out.println(record.value());
                System.out.println(record.partition());
                System.out.println(record.offset());
                System.out.println("-------------------------------------------");
            }
        }
    }

    private static Properties properties() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, FraudDetectorService.class.getSimpleName());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG , "2");
        return props;
    }

}

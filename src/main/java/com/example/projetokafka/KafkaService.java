package com.example.projetokafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Pattern;

public class KafkaService {

    private final ConsumerFunction parse;
    private KafkaConsumer<String, String> consumer;

    KafkaService(String nomeClasse, String topic, ConsumerFunction parse) {
        this.parse = parse;
        this.consumer = new KafkaConsumer<String, String>(properties(nomeClasse));
        consumer.subscribe(Collections.singletonList(topic));
    }
    public KafkaService(String nomeClasse, Pattern compile, ConsumerFunction parse) {
        this.parse = parse;
        this.consumer = new KafkaConsumer<String, String>(properties(nomeClasse));
        consumer.subscribe(Pattern.compile("ECOMMERCE.*"));
    }

    void run() {
        while (true) {
            var records = consumer.poll(Duration.ofMillis(5000));
            if (records.isEmpty()) {
                System.out.println("Sem registro encontrados");
            }
            for (var record : records) {
                parse.consume(record);
            }
        }
    }

    private static Properties properties(String nomeClasse) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, nomeClasse);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG , "2");
        return props;
    }
}

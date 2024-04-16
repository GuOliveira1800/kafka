package com.example.projetokafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class ProjetoKafkaApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //SpringApplication.run(ProjetoKafkaApplication.class, args);

        var kafkaProducer = new KafkaProducer<String, String>(properties());

        Callback callback = (data,exception) ->{
            if(exception != null){
                System.out.println(exception.getMessage());
            }
            System.out.println(data.topic() + " | " + data.partition() + " | " + data.offset());
        };

        for (int i = 0; i < 100; i++) {
            var key = UUID.randomUUID().toString();
            var record = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", key+"_id", "teste");
            var emailRecord = new ProducerRecord<>("ECOMMERCE_SEND_EMAIL", key+"_email", "gu.oliveirabdjc@gmail.com");
            kafkaProducer.send(record, callback).get();
            kafkaProducer.send(emailRecord, callback).get();
        }
    }

    private static Properties properties() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return props;
    }

}

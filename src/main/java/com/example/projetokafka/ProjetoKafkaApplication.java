package com.example.projetokafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class ProjetoKafkaApplication {

    public static String ECOMMERCE_NEW_ORDER = "ECOMMERCE_NEW_ORDER";
    public static String ECOMMERCE_SEND_EMAIL = "ECOMMERCE_SEND_EMAIL";

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        //SpringApplication.run(ProjetoKafkaApplication.class, args);

        var OrderDispatcher = new KafkaDispatcher<NewOrder>();
        var EmailDispatcher = new KafkaDispatcher<String>();

        Callback callback = (data,exception) ->{
            if(exception != null){
                System.out.println(exception.getMessage());
            }
            System.out.println(data.topic() + " | " + data.partition() + " | " + data.offset());
        };


        for (int i = 0; i < 11; i++) {
            var idUsuario = UUID.randomUUID().toString();
            var idCompra = UUID.randomUUID().toString();
            var valor = new BigDecimal(Math.random() * 500 + 1);
            var order = new NewOrder(idUsuario, idCompra, valor);
            OrderDispatcher.send(ECOMMERCE_NEW_ORDER,"id_"+idCompra,order, callback);
            EmailDispatcher.send(ECOMMERCE_SEND_EMAIL,"id_"+idCompra,idUsuario, callback);
        }
        OrderDispatcher.close();
        EmailDispatcher.close();

    }
}

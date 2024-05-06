package com.example.projetokafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class ProjetoKafkaApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        //SpringApplication.run(ProjetoKafkaApplication.class, args);

        // Gera um pedido aleatorizado de exemplo, para fins de demonstração do Kafka
        NewOrderMain newOrderMain = new NewOrderMain();
        var amount = new BigDecimal(Math.random() * 1000 + 1).setScale(2, BigDecimal.ROUND_HALF_UP);
        newOrderMain.newOrder(
            new NewOrder("1","1",amount),
            new Email("teste","novo email de teste","gu.oliveirabdjc@gmail.com","rh@gmail.com")
        );
    }
}

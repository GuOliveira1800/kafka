package com.example.projetokafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public class EmailService {

    // Definição do tópico
    public static String ECOMMERCE_SEND_EMAIL = "ECOMMERCE_SEND_EMAIL";

    public static void main(String[] args) {
        var emailService = new EmailService();

        // Configuração e inicialização do serviço Kafka para consumir mensagens do tópico ECOMMERCE_SEND_EMAIL
        KafkaService kafkaService = new KafkaService<>(
                EmailService.class.getSimpleName(),        // Identificador do consumidor Kafka (usado para gerenciar o grupo de consumidores)
                ECOMMERCE_SEND_EMAIL,                      // Nome do tópico Kafka a ser consumido
                emailService::parse,                       // Callback para processar as mensagens recebidas
                Email.class,                               // Tipo da chave da mensagem Kafka
                Map.of());                                 // Configurações adicionais (nesse caso, não tem)
        kafkaService.run();                                // Inicia o serviço Kafka para consumir mensagens do tópico especificado
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

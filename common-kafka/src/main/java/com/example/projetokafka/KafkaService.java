package com.example.projetokafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.Closeable;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Pattern;

public class KafkaService<T> implements Closeable {
    private final KafkaConsumer<String, T> consumer;
    private final ConsumerFunction parse;

    // Construtor do consumidor Kafka para um tópico específico via string
    public KafkaService(String groupId, String topic, ConsumerFunction parse, Class<T> type, Map<String, String> config) {
        this(parse, groupId, type, config);
        consumer.subscribe(Collections.singletonList(topic));
    }

    // Construtor do consumidor Kafka para um conjunto de tópicos via padrão
    public KafkaService(String groupId, Pattern topic, ConsumerFunction parse, Class<T> type, Map<String, String> config) {
        this(parse, groupId, type, config);
        consumer.subscribe(topic);
    }

    // Construtor utilizado internamente para configurar o consumidor
    private KafkaService(ConsumerFunction parse, String groupId, Class<T> type, Map<String, String> config) {
        this.parse = parse;
        this.consumer = new KafkaConsumer<>(getProperties(type, groupId, config));
    }

    // Inicia o consumidor para ouvir mensagens dos tópicos inscritos
    public void run() {
        while (true) {
            var records = consumer.poll(Duration.ofMillis(100));
            if (!records.isEmpty()) {
                System.out.println("Encontrei " + records.count() + " registros");
                for (var record : records) {
                    parse.consume(record);
                }
            }
        }
    }

    private Properties getProperties(Class<T> type, String groupId, Map<String, String> config) {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092"); // Endereço do servidor Kafka
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); // Deserializador de chave
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, GsonDeserializer.class.getName()); // Deserializador de valor
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId); // Identificador do grupo de consumidores
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString()); // Identificador do cliente
        properties.setProperty(GsonDeserializer.TYPE_CONFIG, type.getName()); // Tipo de deserialização para o valor da mensagem
        properties.putAll(config); // Configurações adicionais
        return properties;
    }

    @Override
    public void close() {
        consumer.close();
    }
}

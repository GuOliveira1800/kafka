package com.example.projetokafka;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static String ECOMMERCE_NEW_ORDER = "ECOMMERCE_NEW_ORDER";
    public static String ECOMMERCE_SEND_EMAIL = "ECOMMERCE_SEND_EMAIL";

    public void newOrder(NewOrder newOrder, Email email) throws InterruptedException, ExecutionException {
        var OrderDispatcher = new KafkaDispatcher<NewOrder>();
        var EmailDispatcher = new KafkaDispatcher<Email>();

        OrderDispatcher.send(ECOMMERCE_NEW_ORDER,UUID.randomUUID().toString(),newOrder);
        EmailDispatcher.send(ECOMMERCE_SEND_EMAIL,UUID.randomUUID().toString(),email);
    }

}

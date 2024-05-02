package com.example.projetokafka;

import lombok.Data;

@Data
public class Email {
    private final String subject,body,from,to;
}

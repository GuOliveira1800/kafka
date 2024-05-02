package com.example.projetokafka;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewOrder {
    private final String idUsuario, idCompra;
    private final BigDecimal valorTotal;
}

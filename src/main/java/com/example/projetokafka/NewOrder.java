package com.example.projetokafka;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class NewOrder implements Serializable {

    private final String idUsuario, idCompra;
    private final BigDecimal valorTotal;
}

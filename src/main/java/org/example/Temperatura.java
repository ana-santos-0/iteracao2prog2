package org.example;

import java.util.Date;

/**
 * Classe que implementa a medição de Temperatura.
 */
public class Temperatura implements IMedicao {
    private Date data;
    private double valor;

    public Temperatura(Date data, double valor) {
        this.data = data;
        this.valor = valor;
    }

    @Override
    public Date getData() {
        return data;
    }

    @Override
    public double getValor() {
        return valor;
    }

    public String getTipo() {
        return "Temperatura";
    }
}
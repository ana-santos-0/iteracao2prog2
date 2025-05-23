package model;

import java.util.Date;

public class Temperatura implements IMedicao {
    private Date data;
    private double valor;

    public Temperatura(Date data, double valor) {
        this.data = data;
        this.valor = valor;
        if (valor < 30 || valor > 45) {
            throw new IllegalArgumentException("Valor invalido. Tente novamente.");
        }
    }

    @Override
    public Date getData() {
        return data;
    }

    @Override
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }


    public String getTipo() {
        return "Temperatura";
    }
}

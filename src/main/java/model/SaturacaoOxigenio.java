package model;

import java.util.Date;

/**
 * Classe que implementa a medição de Saturação de Oxigénio.
 */
public class SaturacaoOxigenio implements IMedicao {
    private Date data;
    private double valor;

    public SaturacaoOxigenio(Date data, double valor) {
        this.data = data;
        // Garante que o valor está entre 0 e 100
        if (valor > 100) {
            this.valor = 100;
        } else if (valor < 0) {
            this.valor = 0;
        } else {
            this.valor = valor;
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

    public String getTipo() {
        return "Saturação de Oxigénio";
    }

    @Override
    public void setValor(double valor) {
        this.valor =valor;
}
}
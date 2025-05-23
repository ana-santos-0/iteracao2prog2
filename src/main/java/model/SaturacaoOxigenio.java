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
        return "Saturacao";
    }

    @Override
    public void setValor(double valor) {
        this.valor = valor;
    }
}

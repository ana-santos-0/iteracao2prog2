package model;

import java.util.Date;

/**
 * Classe que implementa a medição de Frequência Cardíaca.
 */
public class FrequenciaCardiaca implements IMedicao {
    private Date data;
    private double valor;

    public FrequenciaCardiaca(Date data, double valor) {
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
        return "Frequência Cardíaca";
    }
}
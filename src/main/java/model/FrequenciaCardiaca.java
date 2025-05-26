package model;

import java.util.Date;

/**
 * Classe que implementa a medição de Frequência Cardíaca.
 */
public class FrequenciaCardiaca implements IMedicao {
    private Date data;
    private double valor;

    public FrequenciaCardiaca(Date data, double valor) {
        if (valor < 30 || valor > 200) {
            throw new IllegalArgumentException("Valor inválido. Tente novamente.");
        }
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

    @Override
    public String getTipo() {
        return "Frequencia";
    }


    @Override
    public void setValor(double valor) {
        this.valor = valor;
}

}

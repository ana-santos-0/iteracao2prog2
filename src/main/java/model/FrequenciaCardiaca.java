package model;

import java.util.Date;

/**
 * Classe que implementa a medição de Frequência Cardíaca.
 */
public class FrequenciaCardiaca implements IMedicao {
    //fazer a classe Abstrata
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

    @Override
    public void setValor(double valor) {
        this.valor = valor;
}

}
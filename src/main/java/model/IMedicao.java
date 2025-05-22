package model;

import java.io.Serializable;
import java.util.Date;

/**
 * Interface que obriga à implementação de getData e getValor.
 */
public interface IMedicao extends Serializable {
    Date getData();

    double getValor();

    String getTipo();

    void setValor(double valor);
}
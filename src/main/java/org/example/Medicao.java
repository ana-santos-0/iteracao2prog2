package org.example;

import java.util.Date;

/**
 * Interface que obriga à implementação de getData e getValor.
 */
public interface Medicao {
    Date getData();
    double getValor();
    String getTipo();
}

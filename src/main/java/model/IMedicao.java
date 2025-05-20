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
/*
    public static void scoreDeGravidade(String[][] string1, double[][] string2, int i) {
        double scoreDeGravidade = 0;
        int somabpm = 0, somasat = 0;
        float somatemp = 0;

        // Frequência Cardíaca
        if (string2[i][0] > 160 || string2[i][0] < 30) {
            somabpm = 5; //Maior que 160 ou menor que 30
        } else if (string2[i][0] >= 140 || string2[i][0] < 40) {
            somabpm = 4; //Entre 140-160 ou 30-40
        } else if (string2[i][0] >= 120 || string2[i][0] < 50) {
            somabpm = 3; //Entre 120-140 ou 40-50
        } else if (string2[i][0] >= 100 || string2[i][0] < 60) {
            somabpm = 2; //Entre 100-120 ou 50-60
        } else {
            somabpm = 1; //Entre 60-100
        }

        // Temperatura
        if (string2[i][1] >= 40 || string2[i][1] <= 35) {
            somatemp = 5; // Maior que 40 e menor que 35
        } else if (string2[i][1] >= 39.1 || string2[i][1] < 35.4) {
            somatemp = 4; // Entre 39.1-40 ou 35-35.4
        } else if (string2[i][1] >= 38.1 || string2[i][1] < 36) {
            somatemp = 3; // Entre 38.1-39.0 ou 35.5-36.0
        } else if (string2[i][1] >= 37.6) {
            somatemp = 2; //Entre 37.6 - 38.0
        } else {
            somatemp = 1; //Entre 36.1-37.5
        }

        //  Saturação de Oxigénio em %
        if (string2[i][2] <= 80) {
            somasat = 5; // Menor que 80
        } else if (string2[i][2] <= 85) {
            somasat = 4; //Entre 81 e 85
        } else if (string2[i][2] <= 90) {
            somasat = 3; //Entre 86 e 90
        } else if (string2[i][2] <= 95) {
            somasat = 2; //Entre 91 e 95
        } else {
            somasat = 1; //superior a 95
        }
        scoreDeGravidade = (somabpm * 0.3) + (somatemp * 0.4) + (somasat * 0.3);
        System.out.printf("\nScore de Gravidade do paciente %d: %.2f%n", (i + 1), scoreDeGravidade);
        AvaliacaoScore(scoreDeGravidade);
    }

    //avaliacao do score calculado no metodo anterior
    public static void AvaliacaoScore(double scoreDeGravidade) {
        if (scoreDeGravidade < 2 && scoreDeGravidade >= 1) {
            System.out.println("Gravidade Baixa\n"); //Entre 1 e 2
        } else if (scoreDeGravidade < 3.5 && scoreDeGravidade > 2.1) {
            System.out.println("Gravidade Moderada\n"); //Entre 2.1 e 3.5
        } else if (scoreDeGravidade <= 5 && scoreDeGravidade > 3.6) {
            System.out.println("Gravidade Alta\n"); // Entre 3.6 e 5
        }
        */
}
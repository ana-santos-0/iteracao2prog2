package service;

import java.util.List;
import model.*;

public class Alteracoes {

    public static double calcularScore(Paciente p) {
        double fc = p.calcularMedia("Frequencia");
        double temp = p.calcularMedia("Temperatura");
        double spo2 = p.calcularMedia("Saturacao");

        int scoreFC = pontuarFC(fc);
        int scoreTemp = pontuarTemperatura(temp);
        int scoreSpO2 = pontuarSpO2(spo2);

        return (scoreFC * 0.3) + (scoreTemp * 0.4) + (scoreSpO2 * 0.3);
    }

    private static int pontuarFC(double fc) {
        if (fc < 50 || fc > 130) return 5;
        if (fc < 60 || fc > 120) return 4;
        if (fc <= 100) return 1;
        return 3;
    }

    private static int pontuarTemperatura(double t) {
        if (t < 35 || t > 39.5) return 5;
        if (t < 36 || t > 38.5) return 4;
        if (t <= 37.5) return 1;
        return 3;
    }

    private static int pontuarSpO2(double s) {
        if (s < 85) return 5;
        if (s < 90) return 4;
        if (s >= 95) return 1;
        return 3;
    }
}
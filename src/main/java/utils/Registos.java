package utils;

import model.Paciente;
import model.Registo;

import java.util.Date;
import java.util.List;

public class Registos {

    // Calcula a média de um tipo de dado (frequência, temperatura ou saturação)
    public static double calcularMedia(String tipo, List<Registo> registos, Date inicio, Date fim) {
        double soma = 0;
        int count = 0;

        for (Registo r : registos) {
            if (r.getDataHora().after(inicio) && r.getDataHora().before(fim)) {
                soma += getValorPorTipo(r, tipo);
                count++;
            }
        }

        return count > 0 ? soma / count : 0;
    }

    // Calcula o desvio padrão
    public static double calcularDesvioPadrao(String tipo, List<Registo> registos, Date inicio, Date fim) {
        double media = calcularMedia(tipo, registos, inicio, fim);
        double soma = 0;
        int count = 0;

        for (Registo r : registos) {
            if (r.getDataHora().after(inicio) && r.getDataHora().before(fim)) {
                double valor = getValorPorTipo(r, tipo);
                soma += Math.pow(valor - media, 2);
                count++;
            }
        }

        return count > 0 ? Math.sqrt(soma / count) : 0;
    }

    // Valor mínimo
    public static double calcularMinimo(String tipo, List<Registo> registos, Date inicio, Date fim) {
        double minimo = Double.MAX_VALUE;

        for (Registo r : registos) {
            if (r.getDataHora().after(inicio) && r.getDataHora().before(fim)) {
                double valor = getValorPorTipo(r, tipo);
                if (valor < minimo) {
                    minimo = valor;
                }
            }
        }

        return minimo != Double.MAX_VALUE ? minimo : 0;
    }

    // Valor máximo
    public static double calcularMaximo(String tipo, List<Registo> registos, Date inicio, Date fim) {
        double maximo = Double.MIN_VALUE;

        for (Registo r : registos) {
            if (r.getDataHora().after(inicio) && r.getDataHora().before(fim)) {
                double valor = getValorPorTipo(r, tipo);
                if (valor > maximo) {
                    maximo = valor;
                }
            }
        }

        return maximo != Double.MIN_VALUE ? maximo : 0;
    }

    // Função auxiliar para obter o valor do tipo desejado
    private static double getValorPorTipo(Registo r, String tipo) {
        switch (tipo.toLowerCase()) {
            case "frequencia":
            case "fc":
                return r.getFrequenciaCardiaca();
            case "temperatura":
            case "temp":
                return r.getTemperatura();
            case "saturação":
            case "spo2":
            case "sat":
                return r.getSaturacaoOxigenio();
            default:
                throw new IllegalArgumentException("Tipo de dado desconhecido: " + tipo);
        }
    }

    // Metodo exemplo: classificação global (podes adaptar conforme os critérios)
    public static String classificarPaciente(Paciente paciente, Date inicio, Date fim) {
        List<Registo> registos = paciente.getRegisto();
        double mediaSat = calcularMedia("saturação", registos, inicio, fim);

        if (mediaSat < 90) {
            return "Crítico";
        } else if (mediaSat < 95) {
            return "Atenção";
        } else {
            return "Normal";
        }
    }
}

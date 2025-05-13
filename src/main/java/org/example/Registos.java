package org.example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Registos {
    /**
     * Adiciona uma medição à lista de medições do paciente.
     *
     * @param medicao Medição a ser adicionada.
     */

    private List<IMedicao> medicoes = new ArrayList<>();

    public void addMedicao(IMedicao medicao) {
        this.medicoes.add(medicao);
    }

    /**
     * Normaliza uma data para garantir que apenas a parte da data (ano, mês, dia) é considerada.
     */
    private Date normalizarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("aaaa-mm-dd");
        sdf.format(data);
        return data;
    }

    private boolean tipoCorresponde(String tipo, IMedicao medicao) {
        return (tipo.equalsIgnoreCase("Frequencia") && medicao instanceof FrequenciaCardiaca) ||
                (tipo.equalsIgnoreCase("Temperatura") && medicao instanceof Temperatura) ||
                (tipo.equalsIgnoreCase("Saturacao") && medicao instanceof SaturacaoOxigenio);
    }

    private boolean dentroDoIntervalo(Date dataMedicao, Date dataInicio, Date dataFim) {
        return (dataInicio == null || !dataMedicao.before(dataInicio)) &&
                (dataFim == null || !dataMedicao.after(dataFim));
    }

    public double calcularMedia(String tipo, Date dataInicio, Date dataFim) {
        double soma = 0;
        int count = 0;

        Date inicio = dataInicio != null ? normalizarData(dataInicio) : null;
        Date fim = dataFim != null ? normalizarData(dataFim) : null;

        for (IMedicao medicao : medicoes) {
            Date dataMedicao = normalizarData(medicao.getData());

            if (dentroDoIntervalo(dataMedicao, inicio, fim) && tipoCorresponde(tipo, medicao)) {
                soma += medicao.getValor();
                count++;
            }
        }
        if (count == 0) {
            return 0;
        } else {
            return soma / count;
        }
    }

/*
    public double calcularDesvioPadrao(String tipo, Date dataInicio, Date dataFim) {
        double media = calcularMedia(tipo, dataInicio, dataFim);
        double somaQuadrados = 0;
        int count = 0;

        Date inicio = dataInicio != null ? normalizarData(dataInicio) : null;
        Date fim = dataFim != null ? normalizarData(dataFim) : null;

        for (IMedicao medicao : medicoes) {
            Date dataMedicao = normalizarData(medicao.getData());

            if (dentroDoIntervalo(dataMedicao, inicio, fim) && tipoCorresponde(tipo, medicao)) {
                double diff = medicao.getValor() - media;
                somaQuadrados += diff * diff;
                count++;
            }
        }

        if (count == 0) {
            return 0;
        } else {
            double variancia = somaQuadrados / count;
            return Math.sqrt(variancia);
        }

    }

    */
    /*
    public double calcularMinimo(String tipo, Date dataInicio, Date dataFim) {
        double minimo = Double.MAX_VALUE; //???

        // Normaliza as datas de início e fim
        Date dataInicioNormalizada = dataInicio != null ? normalizarData(dataInicio) : null;
        Date dataFimNormalizada = dataFim != null ? normalizarData(dataFim) : null;

        for (IMedicao medicao : medicoes) {
            Date dataMedicaoNormalizada = normalizarData(medicao.getData());

            // Verifica se a medição está dentro do intervalo de datas
            if ((dataInicioNormalizada == null || !dataMedicaoNormalizada.before(dataInicioNormalizada)) &&
                    (dataFimNormalizada == null || !dataMedicaoNormalizada.after(dataFimNormalizada))) {

                if (tipo.equals("Frequencia") && medicao instanceof FrequenciaCardiaca) {
                    minimo = Math.min(minimo, medicao.getValor());
                } else if (tipo.equals("Temperatura") && medicao instanceof Temperatura) {
                    minimo = Math.min(minimo, medicao.getValor());
                } else if (tipo.equals("Saturacao") && medicao instanceof SaturacaoOxigenio) {
                    minimo = Math.min(minimo, medicao.getValor());
                }
            }
        }

        if (minimo == Double.MAX_VALUE) {
            return 0;
        }
        return minimo;

    }
*/
    /**
     * Calcula o valor máximo das medições de um tipo específico, dentro de um intervalo de datas.
     *
     * @param tipo       Tipo de medição (Frequência Cardíaca, Temperatura, Saturação)
     * @param dataInicio Data de início do intervalo (pode ser null)
     * @param dataFim    Data de fim do intervalo (pode ser null)
     * @return O valor máximo das medições dentro do intervalo de datas.
     */
    /*

    METODO COM 0 UTILIZAÇOES


    public double calcularMaximo(String tipo, Date dataInicio, Date dataFim) {
        double maximo = Double.MIN_VALUE;

        // Normaliza as datas de início e fim
        Date dataInicioNormalizada = dataInicio != null ? normalizarData(dataInicio) : null;
        Date dataFimNormalizada = dataFim != null ? normalizarData(dataFim) : null;

        for (IMedicao medicao : medicoes) {
            Date dataMedicaoNormalizada = normalizarData(medicao.getData());

            // Verifica se a medição está dentro do intervalo de datas
            if ((dataInicioNormalizada == null || !dataMedicaoNormalizada.before(dataInicioNormalizada)) &&
                    (dataFimNormalizada == null || !dataMedicaoNormalizada.after(dataFimNormalizada))) {

                if (tipo.equals("Frequencia") && medicao instanceof FrequenciaCardiaca) {
                    maximo = Math.max(maximo, medicao.getValor());
                } else if (tipo.equals("Temperatura") && medicao instanceof Temperatura) {
                    maximo = Math.max(maximo, medicao.getValor());
                } else if (tipo.equals("Saturacao") && medicao instanceof SaturacaoOxigenio) {
                    maximo = Math.max(maximo, medicao.getValor());
                }
            }
        }
        return maximo == Double.MIN_VALUE ? 0 : maximo;
    }*/

    /**
     * Classifica o paciente com base nos sinais vitais.
     *
     * @return A classificação do paciente (Normal, Atenção, Crítico).
     */

    public double calcularMinimo(String tipo, Date dataInicio, Date dataFim) {
        double minimo = Double.MAX_VALUE;

        Date inicio = dataInicio != null ? normalizarData(dataInicio) : null;
        Date fim = dataFim != null ? normalizarData(dataFim) : null;

        for (IMedicao medicao : medicoes) {
            Date dataMedicao = normalizarData(medicao.getData());

            if (dentroDoIntervalo(dataMedicao, inicio, fim) && tipoCorresponde(tipo, medicao)) {
                minimo = Math.min(minimo, medicao.getValor());
            }
        }
        if (minimo == Double.MAX_VALUE) {
            return 0;
        }
        return minimo;
    }


        public String classificarPaciente () {
            double frequenciaCardiacaMedia = calcularMedia("Frequencia", null, null);
            double temperaturaMedia = calcularMedia("Temperatura", null, null);
            double saturacaoMedia = calcularMedia("Saturacao", null, null);

            if (frequenciaCardiacaMedia < 60 || frequenciaCardiacaMedia > 120 || temperaturaMedia < 36 || temperaturaMedia > 38.5 || saturacaoMedia < 90) {
                return "Crítico";
            } else if (frequenciaCardiacaMedia >= 100 || temperaturaMedia >= 37.5 || (saturacaoMedia >= 90 && saturacaoMedia < 95)) {
                return "Atenção";
            } else {
                return "Normal";
            }
/*
        public abstract class Medicao {
            private Date data;
            private double valor;

            public Medicao(Date data, double valor) {
                this.data = data;
                this.valor = valor;
            }

            public Date getData() {
                return data;
            }

            public double getValor() {
                return valor;
            }

            public abstract String getTipo();
        }
        */
        }
    }
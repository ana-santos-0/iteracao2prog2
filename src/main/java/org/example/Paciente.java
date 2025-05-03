package org.example;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Classe que representa um Paciente, extendendo Pessoa.
 * Contém medições de sinais vitais e métodos para calcular a média, desvio padrão, mínimo, máximo e classificar o paciente.
 */
public class Paciente extends Pessoa {
    //public static Paciente novoPaciente;
    private List<Medicao> medicoes;
    private double altura;
    private double peso;

    public Paciente(String nome, Date dataNascimento, double altura, double peso) {
        super(nome, dataNascimento);
        this.altura = altura;
        this.peso = peso;
        medicoes = new ArrayList<Medicao>();

    }
        public double getAltura () {
            return altura;
        }

        public double getPeso () {
            return peso;
        }

    public static LocalDate normalizarData(String dataString) {
        // Define o formato da data que estamos esperando (dd/MM/yyyy)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Converte a string para LocalDate
        LocalDate localDate = LocalDate.parse(dataString, formatter);

        return localDate;
    }



    /**
     * Adiciona uma medição à lista de medições do paciente.
     *
     * @param medicao Medição a ser adicionada.
     */
    protected void addMedicao(Medicao medicao) {
        medicoes.add(medicao);
    }

    /**
     * Normaliza uma data para garantir que apenas a parte da data (ano, mês, dia) é considerada.
     */
    private Date normalizarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.format(data);
        return data;
    }

    public static void infoPaciente() {
        System.out.print("\nNome do paciente: ");
        Scanner scanner = new Scanner(System.in);
        String nomePaciente = scanner.nextLine();
        System.out.println("Insira a data de nascimento (dd/mm/yyyy): ");
        String[] partes = scanner.nextLine().split("/");
        Date dataPac = new Date(Integer.parseInt(partes[2]) - 1900, Integer.parseInt(partes[1]) - 1, Integer.parseInt(partes[0]));
        System.out.print("Insira a altura (cm): ");
        double altPac = scanner.nextDouble();
        System.out.print("Insira o peso (kg): ");
        double pesPac = scanner.nextDouble();
        scanner.nextLine();
        Paciente novoPaciente = new Paciente(nomePaciente, dataPac, altPac, pesPac);
        System.out.println("Paciente adicionado com sucesso!");
    }

    /**
     * Calcula a média dos valores das medições de um tipo específico, dentro de um intervalo de datas.
     *
     * @param tipo       Tipo de medição (Frequência Cardíaca, Temperatura, Saturação)
     * @param dataInicio Data de início do intervalo (pode ser null)
     * @param dataFim    Data de fim do intervalo (pode ser null)
     * @return A média das medições dentro do intervalo de datas.
     */
    public double calcularMedia(String tipo, Date dataInicio, Date dataFim) {
        double soma = 0;
        int count = 0;

        // Normaliza as datas de início e fim para comparar apenas a parte da data (ano, mês, dia)
        Date dataInicioNormalizada = dataInicio != null ? normalizarData(dataInicio) : null;
        Date dataFimNormalizada = dataFim != null ? normalizarData(dataFim) : null;

        for (Medicao medicao : medicoes) {
            Date dataMedicaoNormalizada = normalizarData(medicao.getData());

            // Verifica se a medição está dentro do intervalo de datas
            if ((dataInicioNormalizada == null || !dataMedicaoNormalizada.before(dataInicioNormalizada)) &&
                    (dataFimNormalizada == null || !dataMedicaoNormalizada.after(dataFimNormalizada))) {

                if (tipo.equals("Frequencia") && medicao instanceof FrequenciaCardiaca) {
                    soma += medicao.getValor();
                    count++;
                } else if (tipo.equals("Temperatura") && medicao instanceof Temperatura) {
                    soma += medicao.getValor();
                    count++;
                } else if (tipo.equals("Saturacao") && medicao instanceof SaturacaoOxigenio) {
                    soma += medicao.getValor();
                    count++;
                }
            }
        }

        return count > 0 ? soma / count : 0;
    }

    /**
     * Calcula o desvio padrão das medições de um tipo específico, dentro de um intervalo de datas.
     *
     * @param tipo       Tipo de medição (Frequência Cardíaca, Temperatura, Saturação)
     * @param dataInicio Data de início do intervalo (pode ser null)
     * @param dataFim    Data de fim do intervalo (pode ser null)
     * @return O desvio padrão das medições dentro do intervalo de datas.
     */
    public double calcularDesvioPadrao(String tipo, Date dataInicio, Date dataFim) {
        double media = calcularMedia(tipo, dataInicio, dataFim);
        double somaQuadrados = 0;
        int count = 0;

        // Normaliza as datas de início e fim
        Date dataInicioNormalizada = dataInicio != null ? normalizarData(dataInicio) : null;
        Date dataFimNormalizada = dataFim != null ? normalizarData(dataFim) : null;

        for (Medicao medicao : medicoes) {
            Date dataMedicaoNormalizada = normalizarData(medicao.getData());

            // Verifica se a medição está dentro do intervalo de datas
            if ((dataInicioNormalizada == null || !dataMedicaoNormalizada.before(dataInicioNormalizada)) &&
                    (dataFimNormalizada == null || !dataMedicaoNormalizada.after(dataFimNormalizada))) {

                if (tipo.equals("Frequencia") && medicao instanceof FrequenciaCardiaca) {
                    somaQuadrados += Math.pow(medicao.getValor() - media, 2);
                    count++;
                } else if (tipo.equals("Temperatura") && medicao instanceof Temperatura) {
                    somaQuadrados += Math.pow(medicao.getValor() - media, 2);
                    count++;
                } else if (tipo.equals("Saturacao") && medicao instanceof SaturacaoOxigenio) {
                    somaQuadrados += Math.pow(medicao.getValor() - media, 2);
                    count++;
                }
            }
        }

        return count > 0 ? Math.sqrt(somaQuadrados / count) : 0;
    }

    /**
     * Calcula o valor mínimo das medições de um tipo específico, dentro de um intervalo de datas.
     *
     * @param tipo       Tipo de medição (Frequência Cardíaca, Temperatura, Saturação)
     * @param dataInicio Data de início do intervalo (pode ser null)
     * @param dataFim    Data de fim do intervalo (pode ser null)
     * @return O valor mínimo das medições dentro do intervalo de datas.
     */
    public double calcularMinimo(String tipo, Date dataInicio, Date dataFim) {
        double minimo = Double.MAX_VALUE;

        // Normaliza as datas de início e fim
        Date dataInicioNormalizada = dataInicio != null ? normalizarData(dataInicio) : null;
        Date dataFimNormalizada = dataFim != null ? normalizarData(dataFim) : null;

        for (Medicao medicao : medicoes) {
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

        return minimo == Double.MAX_VALUE ? 0 : minimo;
    }

    /**
     * Calcula o valor máximo das medições de um tipo específico, dentro de um intervalo de datas.
     *
     * @param tipo       Tipo de medição (Frequência Cardíaca, Temperatura, Saturação)
     * @param dataInicio Data de início do intervalo (pode ser null)
     * @param dataFim    Data de fim do intervalo (pode ser null)
     * @return O valor máximo das medições dentro do intervalo de datas.
     */
    public double calcularMaximo(String tipo, Date dataInicio, Date dataFim) {
        double maximo = Double.MIN_VALUE;

        // Normaliza as datas de início e fim
        Date dataInicioNormalizada = dataInicio != null ? normalizarData(dataInicio) : null;
        Date dataFimNormalizada = dataFim != null ? normalizarData(dataFim) : null;

        for (Medicao medicao : medicoes) {
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
    }

    /**
     * Classifica o paciente com base nos sinais vitais.
     *
     * @return A classificação do paciente (Normal, Atenção, Crítico).
     */
    public String classificarPaciente() {
        double frequenciaCardiacaMedia = calcularMedia("Frequencia", null, null);
        double temperaturaMedia = calcularMedia("Temperatura", null, null);
        double saturacaoMedia = calcularMedia("Saturacao", null, null);

        if (frequenciaCardiacaMedia < 60 || frequenciaCardiacaMedia > 120 ||
                temperaturaMedia < 36 || temperaturaMedia > 38.5 ||
                saturacaoMedia < 90) {
            return "Crítico";
        } else if (frequenciaCardiacaMedia >= 100  || temperaturaMedia >= 37.5 || saturacaoMedia >= 90 && saturacaoMedia < 95) {
            return "Atenção";
        } else {
            return "Normal";
        }
    }
}

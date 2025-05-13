package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Hospital {
    private List<Paciente> pacientes;
    private List<TecnicoSaude> tecnicosDeSaude;  // Lista de técnicos de saúde

    public Hospital() {
        this.pacientes = new ArrayList<>();
        this.tecnicosDeSaude = new ArrayList<>();
    }


    // Adiciona um paciente à lista do hospital
    public void addPaciente(Paciente paciente) {
        if (paciente != null) {
            pacientes.add(paciente);
            System.out.println("Paciente " + paciente.getNome() + " adicionado com sucesso.");
        }
    }

    // Adiciona um técnico de saúde à lista do hospital
    public void addTecnicoSaude(TecnicoSaude tecnico) {
        if (tecnico != null) {
            tecnicosDeSaude.add(tecnico);
            System.out.println("Técnico de Saúde " + tecnico.getNome() + " adicionado com sucesso.");
        }
    }

    // Lista todos os técnicos de saúde
    public void listarTecnicos() {
        if (tecnicosDeSaude.isEmpty()) {
            System.out.println("Nenhum técnico de saúde registado.");
            return;
        }

        for (TecnicoSaude t : tecnicosDeSaude) {
            System.out.println(t.getNome());
        }
    }

    // Cálculos para um intervalo de datas e opcionalmente um filtro por nomes
    public void calcularDados(Date dataInicio, Date dataFim, List<String> nomes, String sortBy) {
        List<Paciente> pacientesSelecionados = new ArrayList<>();

        // Se a lista de nomes não estiver vazia, filtra os pacientes
        if (nomes != null && !nomes.isEmpty()) {
            for (Paciente paciente : pacientes) {
                for (String nome : nomes) {
                    if (nomes.contains(paciente.getNome())) {
                        pacientesSelecionados.add(paciente);
                    }
                }
            }
        } else {
            pacientesSelecionados = new ArrayList<>(pacientes);
        }

        // Sorting logic
        if (sortBy != null) {
            switch (sortBy) {
                case "Asc-Name":
                    Collections.sort(pacientesSelecionados, Comparator.comparing(Paciente::getNome));
                    break;
                case "Desc-Name":
                    Collections.sort(pacientesSelecionados, Comparator.comparing(Paciente::getNome).reversed());
                    break;
                case "Asc-Date":
                    Collections.sort(pacientesSelecionados, Comparator.comparing(Paciente::getDataNascimento));
                    break;
                case "Desc-Date":
                    Collections.sort(pacientesSelecionados, Comparator.comparing(Paciente::getDataNascimento).reversed());
                    break;
                default:
                    break;
            }
        }

        String[] tipos = {"Frequencia", "Temperatura", "Saturação"};

        for (String tipo : tipos) {
            System.out.println("Média de " + tipo + ": " + Registos.calcularMedia(tipo, dataInicio, dataFim));
            System.out.println("Desvio Padrão de " + tipo + ": " + Registos.calcularDesvioPadrao(tipo, dataInicio, dataFim));
            System.out.println("Mínimo de " + tipo + ": " + Registos.calcularMinimo(tipo, dataInicio, dataFim));
            System.out.println("Máximo de " + tipo + ": " + Registos.calcularMaximo(tipo, dataInicio, dataFim));
            System.out.println();
        }

        // Classificação do paciente
        System.out.println("Classificação: " + Registos.classificarPaciente());
        System.out.println("----------------------------------");
    }

        // Exibe os dados calculados para os pacientes selecionados
        /*for (Paciente paciente : pacientesSelecionados) {
            System.out.println("Paciente: " + paciente.getNome());
            Registos registos = paciente.getRegistos();

            // Exemplo de cálculos de dados (frequência cardíaca, temperatura, etc.)
            System.out.println("Média de Frequência Cardíaca: " + Registos.calcularMedia("Frequencia", dataInicio, dataFim));
            System.out.println("Desvio Padrão de Frequência Cardíaca: " + Registos.calcularDesvioPadrao("Frequencia", dataInicio, dataFim));
            System.out.println("Mínimo de Frequência Cardíaca: " + Registos.calcularMinimo("Frequencia", dataInicio, dataFim));
            System.out.println("Máximo de Frequência Cardíaca: " + Registos.calcularMaximo("Frequencia", dataInicio, dataFim));

            System.out.println("Classificação: " + Registos.classificarPaciente());
            System.out.println("----------------------------------");
        }

        for (Paciente paciente : pacientesSelecionados) {
            System.out.println("Paciente: " + paciente.getNome());

            // Exemplo de cálculos de dados (frequência cardíaca, temperatura, etc.)
            System.out.println("Média de Temperatura: " + Registos.calcularMedia("Temperatura", dataInicio, dataFim));
            System.out.println("Desvio Padrão de Temperatura: " + Registos.calcularDesvioPadrao("Temperatura", dataInicio, dataFim));
            System.out.println("Mínimo de Temperatura: " + Registos.calcularMinimo("Temperatura", dataInicio, dataFim));
            System.out.println("Máximo de Temperatura: " + Registos.calcularMaximo("Temperatura", dataInicio, dataFim));

            System.out.println("Classificação: " + Registos.classificarPaciente());
            System.out.println("----------------------------------");
        }
        for (Paciente paciente : pacientesSelecionados) {
            System.out.println("Paciente: " + paciente.getNome());

            // Exemplo de cálculos de dados (frequência cardíaca, temperatura, etc.)
            System.out.println("Média de Saturação: " + Registos.calcularMedia("Saturação", dataInicio, dataFim));
            System.out.println("Desvio Padrão de Saturação: " + Registos.calcularDesvioPadrao("Saturação", dataInicio, dataFim));
            System.out.println("Mínimo de Saturação: " + Registos.calcularMinimo("Saturação", dataInicio, dataFim));
            System.out.println("Máximo de Saturação: " + Registos.calcularMaximo("Saturação", dataInicio, dataFim));


            System.out.println("Classificação: " + Registos.classificarPaciente());
            System.out.println("----------------------------------");
        }
    }
*/
    public Paciente getPacientePorNome(String nome) {
        for (Paciente p : pacientes) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    public TecnicoSaude getTecnicoPorNome(String nome) {
        for (TecnicoSaude t : tecnicosDeSaude) {
            if (t.getNome().equalsIgnoreCase(nome)) return t;
        }
        return null;
    }

    public void lerficheiro() {
        try {
            File ficheiro = new File("dados.txt");
            Scanner sc = new Scanner(ficheiro);
            int criticos = 0, pacientesIntroduzidos = 0;
            ResultadoAvaliacao res = new ResultadoAvaliacao(0,0);
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                String[] dados = linha.split(","); // separa por vírgulas

                String nome = dados[0];
                double fc = Double.parseDouble(dados[1]);
                double temp = Double.parseDouble(dados[2]);
                double spo2 = Double.parseDouble(dados[3]);
                res = avaliacaoCriticos(res.criticos, res.pacientesIntroduzidos, fc, temp, spo2);
                System.out.println("Paciente: " + nome);
                System.out.println("FC: " + fc + ", Temp: " + temp + ", Saturação: " + spo2);
                System.out.println("----------");


            }
            percentagemCriticos(res != null ? res.criticos : 0, res != null ? res.pacientesIntroduzidos : 0);
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado!");
            e.printStackTrace();
        }
    }

    //avaliação dos pacientes consoante os seus resultados de fc, sat e temp
    public ResultadoAvaliacao avaliacaoCriticos(int criticos, int pacientesIntroduzidos, double fc, double temp, double spo2) {
        if (fc > 0 && temp > 0 && spo2 > 0) {
            pacientesIntroduzidos++; // Se os dados forem válidos, incrementa o contador

            //frequência cardíaca
            if (fc < 60 || fc > 120) {
                criticos++;
            }
            //temperatura
            else if (temp < 36 || temp > 38.5) {
                criticos++;
            }
            //saturação
            else if (spo2 < 90) {
                criticos++;
            }
        }
        return new ResultadoAvaliacao(criticos, pacientesIntroduzidos);

    }

    public class ResultadoAvaliacao {
        public int criticos;
        public int pacientesIntroduzidos;

        public ResultadoAvaliacao(int criticos, int pacientesIntroduzidos) {
            this.criticos = criticos;
            this.pacientesIntroduzidos = pacientesIntroduzidos;
        }
    }

    //percentagem de criticos tendo em conta o numero total de pacientes
    public void percentagemCriticos(int criticos, int pacientesIntroduzidos) {
        float percentagem = 0;
        if (criticos > 0 && pacientesIntroduzidos > 0) {
            percentagem = ((float) criticos / pacientesIntroduzidos) * 100;
            System.out.println("Percentagem de Pacientes em Situação Crítica: " + percentagem);
            System.out.println("------------------");

        }
    }

    // gerar o gráfico simples
    public static void grafico(int bpm, float temp, float sat, int registo) {
        System.out.println("\nGráfico para o registo nº " + registo);
        System.out.println("Frequência cardíaca (bpm): " + printStars(bpm));
        System.out.println("Temperatura Corporal (ºC): " + printStars(temp));
        System.out.println("Saturação de Oxigénio (%): " + printStars(sat));
    }

    // gerar gráfico mais complexo onde indica o min e o max dentro do estado em que
    // o valor se encontra
    public static void graficoComplexo(int bpm, float temp, float sat, int registo) {

        // Obter os intervalos para cada parâmetro
        int[] bpmIntervalo = obterIntervaloBPM(bpm);
        float[] tempIntervalo = obterIntervaloTemp(temp);
        float[] satIntervalo = obterIntervaloSat(sat);

        // Geração do gráfico com os valores ajustados para cada estado
        System.out.println("\nGráfico complexo para o registo nº " + registo);
        System.out.println(
                "Frequência cardíaca (bpm): " + bpmIntervalo[0] + " " + printStars(bpm) + " " + bpmIntervalo[1] + " ");
        saida(bpm);
        System.out.println(
                "Temperatura Corporal (ºC): " + tempIntervalo[0] + " " + printStars(temp) + " " + tempIntervalo[1] + " ");
        saida2(temp);
        System.out.println(
                "Saturação de Oxigénio (%): " + satIntervalo[0] + " " + printStars(sat) + " " + satIntervalo[1] + " ");
        saida3(sat);
    }

    public static String printStars(float valor) {
        int numStars = (int) (valor / 10); // Cada estrela representa 10 unidades
        return "*".repeat(numStars); // Preenche o restante com espaços
    }

    // Funções para obter intervalos
    public static int[] obterIntervaloBPM(int bpm) {
        if (bpm < 60) {// Crítico (abaixo de 60)
            return new int[]{0, 60};
        } else if (bpm > 120) {
            return new int[]{120, 200}; // Crítico (acima de 120)
        } else if (bpm >= 100) {
            return new int[]{100, 120}; // Atenção
        } else {
            return new int[]{60, 100}; // Normal
        }
    }

    public static float[] obterIntervaloTemp(float temp) {
        if (temp < 36) {
            return new float[]{0, 36}; // Crítico (abaixo de 36)
        } else if (temp > 38.5) {
            return new float[]{38.5f, 42}; // Crítico (acima de 38.5)
        } else if (temp >= 37.5) {
            return new float[]{37.5f, 38.5f}; // Atenção
        } else {
            return new float[]{36.0f, 37.5f}; // Normal
        }
    }

    public static float[] obterIntervaloSat(float sat) {
        if (sat < 90) {
            return new float[]{0, 90}; // Crítico
        } else if (sat >= 95) {
            return new float[]{95, 100}; // Normal
        } else {
            return new float[]{90, 95}; // Atenção
        }
    }

    // atribuição no gráfico do estado para cada variável
    public static void saida(int bpm) {
        if (bpm < 60 || bpm > 120) {
            System.out.println("Crítico");
        } else if (bpm >= 100) {
            System.out.println("Atenção");
        } else
            System.out.println("Normal");
    }

    public static void saida2(float temp) {
        if (temp < 36 || temp > 38.5) {
            System.out.println("Crítico");
        } else if (temp >= 37.5) {
            System.out.println("Atenção");
        } else
            System.out.println("Normal");
    }

    public static void saida3(float sat) {
        if (sat < 90) {
            System.out.println("Crítico");
        } else if (sat >= 95) {
            System.out.println("Normal");
        } else
            System.out.println("Atenção");
    }

}

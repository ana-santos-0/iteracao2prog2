package main;

import model.*;
import service.Alteracoes;
import utils.Graficos;
import utils.Serializable;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static final List<Paciente> pacientes = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            mostrarMenu();
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> adicionarPaciente();
                case 2 -> registarMedicao();
                case 3 -> guardarDados();
                case 4 -> carregarDados();
                case 5 -> simularAlteracoes();
                case 6 -> mostrarScoreGravidade();
                case 7 -> mostrarGraficoPaciente();
                case 8 -> mostrarPercentagemCriticos();
                case 9 -> importarFicheiroDePacientes();
                case 0 -> System.out.println("A sair...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Adicionar paciente");
        System.out.println("2. Registar medição");
        System.out.println("3. Guardar pacientes");
        System.out.println("4. Carregar pacientes");
        System.out.println("5. Simular alterações");
        System.out.println("6. Calcular score de gravidade");
        System.out.println("7. Mostrar gráfico de medições");
        System.out.println("8. Ver percentagem de pacientes críticos");
        System.out.println("9. Importar ficheiro com dados de pacientes");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void adicionarPaciente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        Date dataNascimento = lerData(scanner.nextLine());

       System.out.print("Altura (em metros, e com um . a marcar os valores decimais): ");
        double altura = Double.parseDouble(scanner.nextLine());
        if (altura < 1.0 || altura > 2.5) {
            System.out.println("Altura inválida.");
            return;
        }

        System.out.print("Peso (em kg): ");
        double peso = Double.parseDouble(scanner.nextLine());
        if (peso < 30 || peso > 150) {
            System.out.println("Peso inválido.");
            return;
        }

        pacientes.add(new Paciente(nome, dataNascimento, altura, peso));
        System.out.println("Paciente adicionado.");
    }

    private static void registarMedicao() {
        Paciente p = escolherPaciente();
        if (p == null) return;

        // Escolher tipo de medição por número
        System.out.println("Escolha o tipo de medição:");
        System.out.println("1. Frequência Cardíaca");
        System.out.println("2. Temperatura Corporal");
        System.out.println("3. Saturação de Oxigénio");
        System.out.print("Opção: ");
        int opcaoTipo = Integer.parseInt(scanner.nextLine());

        String tipo;
        switch (opcaoTipo) {
            case 1 -> tipo = "Frequencia";
            case 2 -> tipo = "Temperatura";
            case 3 -> tipo = "Saturacao";
            default -> {
                System.out.println("Opção inválida.");
                return;
            }
        }

        System.out.print("Valor: ");
        double valor = Double.parseDouble(scanner.nextLine());

        if (tipo.equals("Frequencia") && (valor < 30 || valor > 200)) {
            System.out.println("Valor inválido. A frequência deve estar entre 30 e 200 bpm.");
            return;
        } else if (tipo.equals("Temperatura") && (valor < 30.0 || valor > 45.0)) {
            System.out.println("Valor inválido. A temperatura deve estar entre 30.0°C e 45.0°C.");
            return;
        } else if (tipo.equals("Saturacao") && (valor < 0 || valor > 100)) {
            System.out.println("Valor inválido. A saturação deve estar entre 0% e 100%.");
            return;
        }

        // Data da medição
        System.out.print("Data da medição (dd/MM/yyyy): ");
        Date data = lerData(scanner.nextLine());

        // Criar medição com base no tipo
        IMedicao medicao = switch (tipo) {
            case "Frequencia" -> new FrequenciaCardiaca(data, valor);
            case "Temperatura" -> new Temperatura(data, valor);
            case "Saturacao" -> new SaturacaoOxigenio(data, valor);
            default -> null;
        };

        if (medicao != null) {
            p.adicionarMedicao(medicao);
            System.out.println("Medição registada com sucesso!");
        } else {
            System.out.println("Erro ao criar a medição. Tente novamente");
        }
    }

    private static void guardarDados() {
        Serializable.guardarPacientes(pacientes, "pacientes");
    }

    private static void carregarDados() {
        List<Paciente> carregados = Serializable.carregarPacientes("pacientes");
        if (carregados != null) {
            pacientes.clear();
            pacientes.addAll(carregados);
            System.out.println("Pacientes carregados com sucesso.");
        }
    }

    private static void simularAlteracoes() {
        System.out.print("Percentagem de alteração (ex: 10 ou -10): ");
        double percentagem = Double.parseDouble(scanner.nextLine());
        Alteracoes.simularAlteracoes(pacientes, percentagem);
    }

    private static void mostrarScoreGravidade() {
        for (Paciente p : pacientes) {
            double score = Alteracoes.calcularScore(p);
            System.out.printf("%s - Score de gravidade: %.2f%n", p.getNome(), score);
        }
    }

    private static void mostrarGraficoPaciente() {
        Paciente p = escolherPaciente();
        if (p == null) return;

        System.out.println("Gráfico de medições de " + p.getNome());
        Graficos.Graficos("Frequência (bpm)",p.calcularMedia("Frequencia"), 100);
        Graficos.Graficos("Temperatura (°C)", p.calcularMedia("Temperatura"), 50);
        Graficos.Graficos("Saturação (%)", p.calcularMedia("Saturacao"), 100);
    }

    private static void mostrarPercentagemCriticos() {
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente registado.");
            return;
        }

        long criticos = pacientes.stream()
                .filter(p -> "Crítico".equalsIgnoreCase(p.classificarPaciente()))
                .count();

        double percentagem = (criticos * 100.0) / pacientes.size();
        System.out.printf("Percentagem de pacientes críticos: %.2f%%%n", percentagem);
    }

    private static Paciente escolherPaciente() {
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente disponível.");
            return null;
        }

        for (int i = 0; i < pacientes.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, pacientes.get(i).getNome());
        }

        System.out.print("Escolha o número do paciente: ");
        int escolha = Integer.parseInt(scanner.nextLine()) - 1;

        if (escolha >= 0 && escolha < pacientes.size()) {
            return pacientes.get(escolha);
        } else {
            System.out.println("Opção inválida.");
            return null;
        }
    }
    private static Date lerData(String input) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.parse(input);        } catch (Exception e) {
            System.out.println("Formato de data inválido.");
            return new Date();
        }
    }
    private static void importarFicheiroDePacientes() {
        try {
            Scanner ficheiro = new Scanner(new File("dados/dados.txt"));
            boolean secaoMedicoes = false;

            while (ficheiro.hasNextLine()) {
                String linha = ficheiro.nextLine().trim();
                if (linha.isEmpty() || linha.startsWith("#")) continue;

                if (linha.equalsIgnoreCase("medicoes")) {
                    secaoMedicoes = true;
                    continue;
                }

                if (!secaoMedicoes) {
                    String[] partes = linha.split(";");
                    if (partes.length == 4) {
                        String nome = partes[0];
                        Date dataNascimento = lerData(partes[1]);
                        double altura = Double.parseDouble(partes[2]);
                        double peso = Double.parseDouble(partes[3]);

                        Paciente paciente = new Paciente(nome, dataNascimento, altura, peso);
                        pacientes.add(paciente);
                        System.out.println("Paciente importado: " + nome);
                    }
                } else {
                    String[] partes = linha.split(";");
                    if (partes.length == 4) {
                        String nomePaciente = partes[0];
                        String tipo = partes[1];
                        double valor = Double.parseDouble(partes[2]);
                        Date data = lerData(partes[3]);

                        Paciente p = pacientes.stream()
                                .filter(pac -> pac.getNome().equalsIgnoreCase(nomePaciente))
                                .findFirst()
                                .orElse(null);

                        if (p != null) {
                            IMedicao medicao = switch (tipo.toLowerCase()) {
                                case "frequencia" -> new FrequenciaCardiaca(data, valor);
                                case "temperatura" -> new Temperatura(data, valor);
                                case "saturacao" -> new SaturacaoOxigenio(data, valor);
                                default -> null;
                            };

                            if (medicao != null) {
                                p.adicionarMedicao(medicao);
                                System.out.println("Medição adicionada a " + nomePaciente + ": " + tipo + " = " + valor);
                            }
                        } else {
                            System.out.println("Paciente não encontrado: " + nomePaciente);
                        }
                    }
                }
            }

            ficheiro.close();
            System.out.println("Importação do ficheiro concluída!");

        } catch (Exception e) {
            System.out.println("Erro ao importar ficheiro: " + e.getMessage());
        }
    }
}

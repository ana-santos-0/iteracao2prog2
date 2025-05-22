package main;

import model.*;
import model.Paciente;
import service.Alteracoes;
import utils.Graficos;
import utils.Serializable;

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
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void adicionarPaciente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        Date dataNascimento = lerData(scanner.nextLine());

        System.out.print("Altura (em metros): ");
        double altura = Double.parseDouble(scanner.nextLine());

        System.out.print("Peso (em kg): ");
        double peso = Double.parseDouble(scanner.nextLine());

        pacientes.add(new Paciente(nome, dataNascimento, altura, peso));
        System.out.println("Paciente adicionado.");
    }

    private static void registarMedicao() {
        Paciente p = escolherPaciente();
        if (p == null) return;

        System.out.print("Tipo de medição (Frequencia, Temperatura, Saturacao): ");
        String tipo = scanner.nextLine();

        System.out.print("Valor: ");
        double valor = Double.parseDouble(scanner.nextLine());

        System.out.print("Data da medição (dd/MM/yyyy): ");
        Date data = lerData(scanner.nextLine());

        IMedicao medicao = switch (tipo.toLowerCase()) {
            case "frequencia" -> new FrequenciaCardiaca(data, valor);
            case "temperatura" -> new Temperatura(data, valor);
            case "saturacao" -> new SaturacaoOxigenio(data, valor);
            default -> null;
        };

        if (medicao != null) {
            p.adicionarMedicao(medicao);
            System.out.println("Medição registada.");
        } else {
            System.out.println("Tipo de medição inválido.");
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
        Graficos.exibirBarra("Frequência (bpm)", p.calcularMedia("Frequencia"));
        Graficos.exibirBarra("Temperatura (°C)", p.calcularMedia("Temperatura"));
        Graficos.exibirBarra("Saturação (%)", p.calcularMedia("Saturacao"));
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
    /// ERRO QUE NÃO SEI CORRIGIR o_O
    private static Date lerData(String input) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.parse(input);        } catch (Exception e) {
            System.out.println("Formato de data inválido.");
            return new Date();
        }
    }
}
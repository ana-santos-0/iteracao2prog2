package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Criar pacientes e técnicos
        Paciente paciente1 = new Paciente("João Silva", new Date(1990, 5, 15), 1.75, 70);
        Paciente paciente2 = new Paciente("Maria Santos", new Date(1985, 2, 10), 1.68, 65);
        Paciente paciente3 = new Paciente("Carlos Oliveira", new Date(1980, 3, 20), 1.80, 80);
        Paciente paciente4 = new Paciente("Ana Costa", new Date(1995, 10, 5), 1.60, 55);

        TecnicoSaude tecnico1 = new TecnicoSaude("Rui Oliveira", new Date(1980, 3, 20), "Enfermeiro");
        TecnicoSaude tecnico2 = new TecnicoSaude("Ana Silva", new Date(1985, 11, 15), "Técnico de Saúde");
//adição de pacientes e técnicos de saude
        Hospital hospital = new Hospital();
        hospital.addPaciente(paciente1);
        hospital.addPaciente(paciente2);
        hospital.addPaciente(paciente3);
        hospital.addPaciente(paciente4);
        hospital.addTecnicoSaude(tecnico1);
        hospital.addTecnicoSaude(tecnico2);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
//menu
        while (!exit) {
            System.out.println("\n=== Sistema de Monitorização Hospitalar ===");
            System.out.println("1. Listar Técnicos de Saúde");
            System.out.println("2. Adicionar Paciente");
            System.out.println("3. Adicionar Técnico de Saúde");
            System.out.println("4. Calcular Dados para Intervalo de Datas");
            System.out.println("5. Adicionar Medição a Paciente");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int escolha = scanner.nextInt();
            scanner.nextLine();
//escolha de opção
            switch (escolha) {
                case 1:
                    System.out.println("\n=== Técnicos de Saúde ===");
                    hospital.listarTecnicos();
                    break;

                case 2:
                    Paciente.infoPaciente();
                    hospital.addPaciente(Paciente.novoPaciente);
                    break;

                case 3:
                    TecnicoSaude novoTecnico = TecnicoSaude.criarTecnicoSaude();
                    hospital.addTecnicoSaude(novoTecnico);

                    break;

                case 4:
                    System.out.print("\nData de início (dd/mm/yyyy): ");
                    String[] partes = scanner.nextLine().split("/");
                    Date dataInicio = new Date(Integer.parseInt(partes[2]) - 1900, Integer.parseInt(partes[1]) - 1, Integer.parseInt(partes[0]));

                    System.out.print("Data de fim (dd/mm/yyyy): ");
                    partes = scanner.nextLine().split("/");
                    Date dataFim = new Date(Integer.parseInt(partes[2]) - 1900, Integer.parseInt(partes[1]) - 1, Integer.parseInt(partes[0]));

                    System.out.print("Deseja filtrar por nomes? (sim/não): ");
                    String resposta = scanner.nextLine();
                    List<String> nomes = null;
                    if (resposta.equalsIgnoreCase("sim")) {
                        nomes = new ArrayList<>();
                        System.out.println("Digite os nomes (um por linha, vazio para terminar): ");
                        while (true) {
                            String nome = scanner.nextLine();
                            if (nome.isEmpty()) break;
                            nomes.add(nome);
                        }
                    }

                    System.out.print("Ordenar por (Asc-Name, Desc-Name, Asc-Date, Desc-Date) ou enter para nenhum: ");
                    String ordem = scanner.nextLine();
                    if (ordem.isEmpty()) ordem = null;

                    hospital.calcularDados(dataInicio, dataFim, nomes, ordem);
                    break;

                case 5:
                    System.out.print("Nome do técnico: ");
                    String nomeTec = scanner.nextLine();
                    TecnicoSaude tecnico = hospital.getTecnicoPorNome(nomeTec);
                    if (tecnico == null) {
                        System.out.println("Técnico não encontrado.");
                        break;
                    }
                    System.out.print("Nome do paciente: ");
                    String nomePaciente = scanner.nextLine();

                    Paciente pacienteSelecionado = hospital.getPacientePorNome(nomePaciente);
                    if (pacienteSelecionado == null) {
                        System.out.println("Paciente não encontrado.");
                        break;
                    }

                    System.out.println("Tipo de medição (1-Frequência Cardíaca, 2-Temperatura, 3-Saturação Oxigénio): ");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Data da medição (dd/mm/yyyy): ");
                    String[] dataParts = scanner.nextLine().split("/");
                    Date data = new Date(Integer.parseInt(dataParts[2]) - 1900, Integer.parseInt(dataParts[1]) - 1, Integer.parseInt(dataParts[0]));

                    double valor = 0;
                    while (valor <= 0)
                    {
                        System.out.print("Valor da medição: ");
                        valor = scanner.nextDouble();
                        scanner.nextLine();

                        if(valor <= 0)
                        {
                            System.out.print("Erro de leitura. Digite novamente.\n");
                        }
                    }

                    IMedicao novaMedicao = null;
                    switch (tipo) {
                        case 1:
                            novaMedicao = new FrequenciaCardiaca(data, (int) valor);
                            break;
                        case 2:
                            novaMedicao = new Temperatura(data, valor);
                            break;
                        case 3:
                            novaMedicao = new SaturacaoOxigenio(data, valor);
                            break;
                        default:
                            System.out.println("Tipo inválido.");
                            break;
                    }

                    if (novaMedicao != null) {
                        tecnico.adicionarMedicao(pacienteSelecionado, novaMedicao); // usando técnico1 como exemplo
                    }
                    if (tipo == 3 && valor > 100) {
                        System.out.println("Nota: Valor de saturação superior a 100 foi ajustado automaticamente para 100.");
                    }
                    break;

                case 6:
                    exit = true;
                    System.out.println("A sair...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}
package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

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
                if (nomes.contains(paciente.getNome())) {
                    pacientesSelecionados.add(paciente);
                }
            }
        } else {
            pacientesSelecionados = pacientes;
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

        // Exibe os dados calculados para os pacientes selecionados
        for (Paciente paciente : pacientesSelecionados) {
            System.out.println("Paciente: " + paciente.getNome());

            // Exemplo de cálculos de dados (frequência cardíaca, temperatura, etc.)
            System.out.println("Média de Frequência Cardíaca: " + paciente.calcularMedia("Frequencia", dataInicio, dataFim));
            System.out.println("Desvio Padrão de Frequência Cardíaca: " + paciente.calcularDesvioPadrao("Frequencia", dataInicio, dataFim));
            System.out.println("Mínimo de Frequência Cardíaca: " + paciente.calcularMinimo("Frequencia", dataInicio, dataFim));
            System.out.println("Máximo de Frequência Cardíaca: " + paciente.calcularMaximo("Frequencia", dataInicio, dataFim));

            System.out.println("Classificação: " + paciente.classificarPaciente());
            System.out.println("----------------------------------");
        }

        for (Paciente paciente : pacientesSelecionados) {
            System.out.println("Paciente: " + paciente.getNome());

            // Exemplo de cálculos de dados (frequência cardíaca, temperatura, etc.)
            System.out.println("Média de Temperatura: " + paciente.calcularMedia("Temperatura", dataInicio, dataFim));
            System.out.println("Desvio Padrão de Temperatura: " + paciente.calcularDesvioPadrao("Temperatura", dataInicio, dataFim));
            System.out.println("Mínimo de Temperatura: " + paciente.calcularMinimo("Temperatura", dataInicio, dataFim));
            System.out.println("Máximo de Temperatura: " + paciente.calcularMaximo("Temperatura", dataInicio, dataFim));

            System.out.println("Classificação: " + paciente.classificarPaciente());
            System.out.println("----------------------------------");
        }
        for (Paciente paciente : pacientesSelecionados) {
            System.out.println("Paciente: " + paciente.getNome());

            // Exemplo de cálculos de dados (frequência cardíaca, temperatura, etc.)
            System.out.println("Média de Saturação: " + paciente.calcularMedia("Saturação", dataInicio, dataFim));
            System.out.println("Desvio Padrão de Saturação: " + paciente.calcularDesvioPadrao("Saturação", dataInicio, dataFim));
            System.out.println("Mínimo de Saturação: " + paciente.calcularMinimo("Saturação", dataInicio, dataFim));
            System.out.println("Máximo de Saturação: " + paciente.calcularMaximo("Saturação", dataInicio, dataFim));


            System.out.println("Classificação: " + paciente.classificarPaciente());
            System.out.println("----------------------------------");
        }
    }

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
}
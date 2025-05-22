package org.example;

import model.Paciente;
import model.TecnicoSaude;
import utils.Registos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Hospital {
    private List<Paciente> pacientes;
    private List<TecnicoSaude> tecnicosDeSaude;

    public Hospital() {
        this.pacientes = new ArrayList<>();
        this.tecnicosDeSaude = new ArrayList<>();
    }

    public void addPaciente(Paciente paciente) {
        if (paciente != null) {
            pacientes.add(paciente);
            System.out.println("Paciente " + paciente.getNome() + " adicionado com sucesso.");
        }
    }

    public void addTecnicoSaude(TecnicoSaude tecnico) {
        if (tecnico != null) {
            tecnicosDeSaude.add(tecnico);
            System.out.println("Técnico de Saúde " + tecnico.getNome() + " adicionado com sucesso.");
        }
    }

    public Paciente getPacientePorNome(String nome) {
        for (Paciente p : pacientes) {
            if (p.getNome().equalsIgnoreCase(nome)) return p;
        }
        return null;
    }

    public TecnicoSaude getTecnicoPorNome(String nome) {
        for (TecnicoSaude t : tecnicosDeSaude) {
            if (t.getNome().equalsIgnoreCase(nome)) return t;
        }
        return null;
    }

    public void listarTecnicos() {
        if (tecnicosDeSaude.isEmpty()) {
            System.out.println("Nenhum técnico de saúde registado.");
            return;
        }

        for (TecnicoSaude t : tecnicosDeSaude) {
            System.out.println(t.getNome());
        }
    }

    public void calcularDados(Date dataInicio, Date dataFim, List<String> nomes, String sortBy) {
        List<Paciente> selecionados = new ArrayList<>();

        Paciente paciente = null;
        if (nomes != null && !nomes.isEmpty()) {//paciente p
            for (Paciente p : pacientes) {
                if (nomes.contains(p.getNome())) {
                    selecionados.add(p);
                    paciente = p;
                }

            }
            if(paciente == null){
                System.out.println("Nenhum paciente encontrado com os nomes fornecidos.");
                return;
            }
        } else {
            selecionados = new ArrayList<>(pacientes);
        }

        if (sortBy != null) {
            switch (sortBy) {
                case "Asc-Name":
                    selecionados.sort(Comparator.comparing(Paciente::getNome));
                    break;
                case "Desc-Name":
                    selecionados.sort(Comparator.comparing(Paciente::getNome).reversed());
                    break;
                case "Asc-Date":
                    selecionados.sort(Comparator.comparing(Paciente::getDataNascimento));
                    break;
                case "Desc-Date":
                    selecionados.sort(Comparator.comparing(Paciente::getDataNascimento).reversed());
                    break;
            }
        }

        String[] tipos = {"Frequencia", "Temperatura", "Saturação"};

        for (String tipo : tipos) {
            //este não é o segundo argumento para o metodo calcularMedia
            System.out.println("Média de " + tipo + ": " + Registos.calcularMedia(tipo, paciente.getRegistos() ,dataInicio, dataFim));
            System.out.println("Desvio Padrão de " + tipo + ": " + Registos.calcularDesvioPadrao(tipo, paciente.getRegistos(), dataInicio, dataFim));
            System.out.println("Mínimo de " + tipo + ": " + Registos.calcularMinimo(tipo, paciente.getRegistos(), dataInicio, dataFim));
            System.out.println("Máximo de " + tipo + ": " + Registos.calcularMaximo(tipo, paciente.getRegistos(), dataInicio, dataFim));
            System.out.println();
        }

        System.out.println("Classificação: " + Registos.classificarPaciente(paciente, dataInicio, dataFim));
        System.out.println("----------------------------------");
    }

    public void lerFicheiro() {
        try {
            File ficheiro = new File("dados.txt");
            Scanner sc = new Scanner(ficheiro);
            int criticos = 0, pacientesIntroduzidos = 0;
            ResultadoAvaliacao res = new ResultadoAvaliacao(0, 0);

            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                String[] dados = linha.split(",");
                String nome = dados[0];
                double fc = Double.parseDouble(dados[1]);
                double temp = Double.parseDouble(dados[2]);
                double spo2 = Double.parseDouble(dados[3]);

                res = avaliacaoCriticos(res.criticos, res.pacientesIntroduzidos, fc, temp, spo2);

                System.out.println("Paciente: " + nome);
                System.out.println("FC: " + fc + ", Temp: " + temp + ", Saturação: " + spo2);
                System.out.println("----------");
            }

            percentagemCriticos(res.criticos, res.pacientesIntroduzidos);
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado!");
        }
    }

    public ResultadoAvaliacao avaliacaoCriticos(int criticos, int pacientesIntroduzidos, double fc, double temp, double spo2) {
        if (fc > 0 && temp > 0 && spo2 > 0) {
            pacientesIntroduzidos++;

            if (fc < 60 || fc > 120 || temp < 36 || temp > 38.5 || spo2 < 90) {
                criticos++;
            }
        }

        return new ResultadoAvaliacao(criticos, pacientesIntroduzidos);
    }

    public void percentagemCriticos(int criticos, int pacientesIntroduzidos) {
        if (pacientesIntroduzidos > 0) {
            float percentagem = ((float) criticos / pacientesIntroduzidos) * 100;
            System.out.println("Percentagem de Pacientes Críticos: " + percentagem + "%");
        }
    }

    public static class ResultadoAvaliacao {
        public int criticos;
        public int pacientesIntroduzidos;

        public ResultadoAvaliacao(int criticos, int pacientesIntroduzidos) {
            this.criticos = criticos;
            this.pacientesIntroduzidos = pacientesIntroduzidos;
        }
    }
}
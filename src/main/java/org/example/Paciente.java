package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Classe que representa um Paciente, extendendo Pessoa.
 * Contém medições de sinais vitais e métodos para calcular a média, desvio padrão, mínimo, máximo e classificar o paciente.
 */

public class Paciente extends Pessoa {
    public static Paciente novoPaciente;
    private List<IMedicao> medicoes;
    private double altura;
    private double peso;
    private Registos registos;

    public Paciente(String nome, Date dataNascimento, double altura, double peso) {
        super(nome, dataNascimento);
        this.altura = altura;
        this.peso = peso;
        medicoes = new ArrayList<IMedicao>();
        this.registos = new Registos();
    }

    public Registos getRegistos() {
        return registos;
    }

    public static void infoPaciente() {
        System.out.print("\nNome do paciente: ");
        Scanner scanner = new Scanner(System.in);
        String nomePaciente = scanner.nextLine();
        System.out.println("Insira a data de nascimento (dd/mm/aaaa): ");
        String[] partes = scanner.nextLine().split("/");
        Date dataPac = new Date(Integer.parseInt(partes[2]), Integer.parseInt(partes[1]) - 1, Integer.parseInt(partes[0]));
        System.out.print("Insira a altura (cm): ");
        double altPac = scanner.nextDouble();
        System.out.print("Insira o peso (kg): ");
        double pesPac = scanner.nextDouble();
        scanner.nextLine();
        Paciente novoPaciente = new Paciente(nomePaciente, dataPac, altPac, pesPac);
        System.out.println("Paciente adicionado com sucesso!");
    }
    public void addMedicao(IMedicao medicao) {
        this.medicoes.add(medicao);
    }

}
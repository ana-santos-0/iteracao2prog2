package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Paciente {
    private String nome;
    private Date dataNascimento;
    private double altura;
    private double peso;
    private List<IMedicao> medicoes = new ArrayList<>();

    // Construtor
    public Paciente(String nome, Date dataNascimento, double altura, double peso) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.altura = altura;
        this.peso = peso;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public double getAltura() {
        return altura;
    }

    public double getPeso() {
        return peso;
    }

    public List<IMedicao> getMedicoes() {
        return medicoes;
    }

    public void adicionarMedicao(IMedicao medicao) {
        medicoes.add(medicao);
    }

    // Métodos adicionais, como para criar um novo paciente
    public static void infoPaciente() {
        // Exemplo de método estático
        System.out.println("Informações sobre o paciente...");
    }

    // Método para criar um novo paciente a partir da entrada do usuário
    public static Paciente novoPaciente(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Data de Nascimento (dd/mm/yyyy): ");
        String[] partes = scanner.nextLine().split("/");
        Date dataNascimento = new Date(Integer.parseInt(partes[2]) - 1900, Integer.parseInt(partes[1]) - 1, Integer.parseInt(partes[0]));

        System.out.print("Altura: ");
        double altura = scanner.nextDouble();
        System.out.print("Peso: ");
        double peso = scanner.nextDouble();
        scanner.nextLine(); // Consumir a linha

        return new Paciente(nome, dataNascimento, altura, peso);
    }
}

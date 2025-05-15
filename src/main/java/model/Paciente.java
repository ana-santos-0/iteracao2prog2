//metodo fechado
package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Paciente extends Pessoa implements Serializable {
    private double altura;
    private double peso;
    private List<IMedicao> medicoes;

    // Construtor
    public Paciente(String nome, Date dataNascimento, double altura, double peso) {
        super(nome, dataNascimento);
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.altura = altura;
        this.peso = peso;
        this.medicoes = new ArrayList<>();
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

    public static void infoPaciente() {
        System.out.println("Informações sobre o paciente...");
    }

    // Metodo para criar um novo paciente a partir da entrada do utilizador
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

    /**
     * Calcula a média das medições de um tipo específico.
     */
    public double calcularMedia(String tipo) {
        double soma = 0;
        int count = 0;

        for (IMedicao m : medicoes) {
            if (m.getTipo().equalsIgnoreCase(tipo)) {
                soma += m.getValor();
                count++;
            }
        }
        if (count == 0) {
            return 0;
        } else {
            return soma / count;
        }
    }

    public double calcularDesvioPadrao(String tipo) {
        double media = calcularMedia(tipo);
        double somaQuadrados = 0;
        int count = 0;

        for (IMedicao m : medicoes) {
            if (m.getTipo().equalsIgnoreCase(tipo)) {
                somaQuadrados += Math.pow(m.getValor() - media, 2);
                count++;
            }
        }
        if (count == 0) {
            return 0;
        } else {
            return Math.sqrt(somaQuadrados / count);
        }

    }

    public double calcularMinimo(String tipo) {
        double minimo = Double.MAX_VALUE;
        boolean encontrado = false;

        for (IMedicao m : medicoes) {
            if (m.getTipo().equalsIgnoreCase(tipo)) {
                minimo = Math.min(minimo, m.getValor());
                encontrado = true;
            }
        }
        if (encontrado) {
            return minimo;
        } else {
            return -1;
        }

    }

    public double calcularMaximo(String tipo) {
        double maximo = Double.MIN_VALUE;
        boolean encontrado = false;

        for (IMedicao m : medicoes) {
            if (m.getTipo().equalsIgnoreCase(tipo)) {
                maximo = Math.max(maximo, m.getValor());
                encontrado = true;
            }
        }
        if (encontrado) {
            return maximo;
        } else {
            return -1;
        }
    }
    public String classificarPaciente() {
        double fc = calcularMedia("Frequencia");
        double temp = calcularMedia("Temperatura");
        double spo2 = calcularMedia("Saturacao");

        if (fc < 60 || fc > 120 || temp < 36 || temp > 38.5 || spo2 < 90) {
            return "Crítico";
        } else if ((fc > 100 && fc <= 120) || (temp > 37.5 && temp <= 38.5) || (spo2 >= 90 && spo2 < 95)) {
            return "Atenção";
        } else {
            return "Normal";
        }
    }

    public String toString() {
        return nome + " (DN: " + new SimpleDateFormat("dd/MM/yyyy").format(dataNascimento)
                + ") Altura: " + altura + "m, Peso: " + peso + "kg";
    }
}
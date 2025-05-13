package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TecnicoSaude extends Pessoa {
    private String categoriaProfissional;
    private List<IMedicao> medicoes = new ArrayList<>();

    public TecnicoSaude(String nome, Date dataNascimento, String categoriaProfissional) {
        super(nome, dataNascimento);
        this.categoriaProfissional = categoriaProfissional;
    }

    /**
     * Metodo para adicionar uma medição ao paciente.
     *
     paciente Paciente ao qual a medição será adicionada.
     medicao  Medição a ser adicionada.
     */

    public void adicionarMedicao(Paciente paciente, IMedicao medicao) {
        if (paciente != null && medicao != null) {
            paciente.addMedicao(medicao);  // Metodo addMedicao é chamado para adicionar a medição ao paciente
            System.out.println("Medição de tipo " + medicao.getClass().getSimpleName() + " foi adicionada ao paciente " + paciente.getNome());
        } else {
            System.out.println("Erro: Paciente ou Medição inválida.");
        }
    }

    public static TecnicoSaude criarTecnicoSaude() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nome do Técnico de Saúde: ");
        String nome = sc.nextLine();
        System.out.print("Categoria profissional: ");
        String categoriaProfissional = sc.nextLine();
        System.out.print("Data de nascimento (dd/mm/yyyy): ");
        String[] dataPartes = sc.nextLine().split("/");
        Date dataNascimento = new Date(Integer.parseInt(dataPartes[2]) - 1900, Integer.parseInt(dataPartes[1]) - 1, Integer.parseInt(dataPartes[0]));


        return new TecnicoSaude(nome, dataNascimento, categoriaProfissional);
    }

}
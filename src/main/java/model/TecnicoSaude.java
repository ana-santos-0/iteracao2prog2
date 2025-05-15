package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

public class TecnicoSaude extends Pessoa {
    private String categoriaProfissional;

    public TecnicoSaude(String nome, Date dataNascimento, String categoriaProfissional) {
        super(nome, dataNascimento);
        this.categoriaProfissional = categoriaProfissional;
    }

    public String getCategoriaProfissional() {
        return categoriaProfissional;
    }

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
        Date dataNascimento = new Date(Integer.parseInt(dataPartes[2]), Integer.parseInt(dataPartes[1]) - 1, Integer.parseInt(dataPartes[0]));


        return new TecnicoSaude(nome, dataNascimento, categoriaProfissional);
    }

}
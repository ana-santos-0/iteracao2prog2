package service;

import java.util.List;
import model.*;

public class Alteracoes {

    public static void aumentarTodosPacientes(List<Paciente> pacientes, double percentagem) {
        for (Paciente paciente : pacientes) {
            for (IMedicao medicao : paciente.getMedicoes()) {
                double valorOriginal = medicao.getValor();
                double variacao = valorOriginal * percentagem / 100.0;
                double novoValor = valorOriginal + variacao;  // aumenta sempre

                // Só altera se for uma instância da classe Medicao
                if (medicao instanceof Medicao) {
                    ((Medicao) medicao).setValor(novoValor);
                }
            }
        }

        System.out.println("Alterações simuladas em todos os pacientes.");
    }

    public static void diminuirTodosPacientes(List<Paciente> pacientes, double percentagem) {
        for (Paciente paciente : pacientes) {
            for (IMedicao medicao : paciente.getMedicoes()) {
                double valorOriginal = medicao.getValor();
                double variacao = valorOriginal * percentagem / 100.0;
                double novoValor = valorOriginal - variacao;  // diminui sempre

                // Só altera se for uma instância da classe Medicao
                if (medicao instanceof Medicao) {
                    ((Medicao) medicao).setValor(novoValor);
                }
            }
        }

        System.out.println("Diminuição simulada em todos os pacientes.");
    }
}

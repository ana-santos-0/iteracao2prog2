package service;

package service;

import java.util.List;
import java.util.Random;
import model.*;

public class SimuladorAlteracoes {

    public static void alterarTodosPacientes(List<Paciente> pacientes, double percentagem) {
        Random rand = new Random();

        for (Paciente paciente : pacientes) {
            for (Medicao medicao : paciente.getMedicoes()) {
                double valorOriginal = medicao.getValor();
                double variacao = valorOriginal * percentagem / 100.0;
                double novaMedicao = rand.nextBoolean()
                        ? valorOriginal + variacao
                        : valorOriginal - variacao;
                medicao.setValor(novaMedicao);
            }
        }

        System.out.println("Alterações simuladas em todos os pacientes.");
    }
}

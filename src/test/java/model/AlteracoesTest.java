package model;

import org.junit.jupiter.api.Test;
import service.Alteracoes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlteracoesTest {

    @Test
    void testSimularAlteracoes() {
        Paciente paciente = new Paciente("Maria", new Date(), 1.78, 59);
        IMedicao medicao = new Temperatura(new Date(), 36.5);
        paciente.addMedicao(medicao);

        List<Paciente> pacientes = new ArrayList<>();
        pacientes.add(paciente);

        Alteracoes.simularAlteracoes(pacientes, 10);

        assertEquals(40.15, paciente.getMedicoes().get(0).getValor(), 0.01);
    }
}



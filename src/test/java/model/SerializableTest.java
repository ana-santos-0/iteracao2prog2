package model;

import org.junit.jupiter.api.Test;
import utils.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SerializableTest {

    @Test
    void testGuardarECarregarPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        Paciente paciente = new Paciente("João", new Date(), 1.78, 58);
        pacientes.add(paciente);

        String caminho = "test_pacientes.dat";
        Serializable.guardarPacientes(pacientes, caminho);

        List<Paciente> carregados = Serializable.carregarPacientes(caminho);
        assertNotNull(carregados);
        assertEquals(1, carregados.size());
        assertEquals("João", carregados.get(0).getNome());
    }
}



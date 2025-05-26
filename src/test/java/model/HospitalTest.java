package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HospitalTest {

    @Test
    public void testAdmitirPaciente() {
        Hospital hospital = new Hospital();
        Paciente paciente = new Paciente("Ana", "04/04/1995", 1.70);
        hospital.admitirPaciente(paciente);

        assertEquals(1, hospital.getPacientes().size());
        assertEquals(paciente, hospital.getPacientes().get(0));
    }

    @Test
    public void testProcurarPacientePorNome() {
        Hospital hospital = new Hospital();
        Paciente paciente = new Paciente("Bruno", "05/05/1980", 1.85);
        hospital.admitirPaciente(paciente);

        Paciente encontrado = hospital.procurarPacientePorNome("Bruno");
        assertNotNull(encontrado);
        assertEquals("Bruno", encontrado.getNome());
    }

    @Test
    public void testRemoverPaciente() {
        Hospital hospital = new Hospital();
        Paciente paciente = new Paciente("Clara", "06/06/1992", 1.60);
        hospital.admitirPaciente(paciente);
        hospital.removerPaciente("Clara");

        assertTrue(hospital.getPacientes().isEmpty());
    }
}

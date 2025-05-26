package model;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PacienteTest {

    @Test
    public void testAdicionarRegistoEFiltroPorTipo() {
        Paciente p = new Paciente("João", "01/01/2000", 1.75);

        p.addMedicao(new Registo("Frequencia", 80));
        p.addMedicao(new Registo("Temperatura", 37.2));
        p.addMedicao(new Registo("Frequencia", 85));

        List<Registo> freq = p.getRegistosPorTipo("Frequencia");

        assertEquals(2, freq.size());
        assertEquals(80, freq.get(0).getRegisto());
        assertEquals(85, freq.get(1).getValor());
    }

    @Test
    public void testCalcularMediaFrequencia() {
        Paciente p = new Paciente("Maria", "01/01/1995", 1.65);

        p.addMedicao(new Registo("Frequencia", 70));
        p.addMedicao(new Registo("Frequencia", 90));

        double media = p.calcularMedia("Frequencia");
        assertEquals(80.0, media);
    }
}

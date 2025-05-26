package model;

import org.junit.jupiter.api.Test;
import utils.Registos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegistosTest {

    private Date parseDate(String s) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(s);
        } catch (Exception e) {
            return new Date();
        }
    }

    @Test
    void testCalcularMedia() {
        List<Registo> registos = new ArrayList<>();
        registos.add(new Registo(80, 36.5, 98, parseDate("01/01/2024")));
        registos.add(new Registo(90, 37.0, 97, parseDate("02/01/2024")));

        double media = Registos.calcularMedia("frequencia", registos,
                parseDate("01/01/2023"), parseDate("03/01/2024"));

        assertEquals(85.0, media, 0.01);
    }

    @Test
    void testCalcularDesvioPadrao() {
        List<Registo> registos = new ArrayList<>();
        registos.add(new Registo(80, 36.5, 98, parseDate("01/01/2024")));
        registos.add(new Registo(90, 37.0, 97, parseDate("02/01/2024")));

        double desvioPadrao = Registos.calcularDesvioPadrao("frequencia", registos,
                parseDate("01/01/2023"), parseDate("03/01/2024"));

        assertEquals(5.0, desvioPadrao, 0.01);
    }

    @Test
    void testCalcularMinimo() {
        List<Registo> registos = new ArrayList<>();
        registos.add(new Registo(80, 36.5, 98, parseDate("01/01/2024")));
        registos.add(new Registo(90, 37.0, 97, parseDate("02/01/2024")));

        double minimo = Registos.calcularMinimo("frequencia", registos,
                parseDate("01/01/2023"), parseDate("03/01/2024"));

        assertEquals(80.0, minimo, 0.01);
    }

    @Test
    void testCalcularMaximo() {
        List<Registo> registos = new ArrayList<>();
        registos.add(new Registo(80, 36.5, 98, parseDate("01/01/2024")));
        registos.add(new Registo(90, 37.0, 97, parseDate("02/01/2024")));

        double maximo = Registos.calcularMaximo("frequencia", registos,
                parseDate("01/01/2023"), parseDate("03/01/2024"));

        assertEquals(90.0, maximo, 0.01);
    }
}
